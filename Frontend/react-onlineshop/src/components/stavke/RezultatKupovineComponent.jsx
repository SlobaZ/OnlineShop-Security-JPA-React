import React, { Component } from 'react'
import KupovineService from '../../services/KupovineService'
import StavkeService from '../../services/StavkeService'
import AuthService from "../../services/auth.service";

class RezultatKupovineComponent extends Component {
    
    constructor(props) {
        super(props)

        this.state = {
        id: this.props.match.params.id,
        	stavke: [],
            user: {},
            kupovina: {},
            
        };
    }
 

    componentDidMount(){
        const user = AuthService.getCurrentUser();
        if (!user) {
            this.props.history.push('/login');
        }
        else{
          StavkeService.getAllsByKupovinaId(this.state.id).then( response => {
            this.setState({stavke: response.data});
          });
          KupovineService.getKupovinaById(this.state.id).then((response) => {
            this.setState({ kupovina: response.data });
          });
            this.setState({
                user: AuthService.getCurrentUser()
              });

        }
    }


    render() {
        const { kupovina } = this.state;
        const { user } = this.state;
        return (
            <div>
                <br/>
                 <h2 className="text-center">Rezultat kupovine</h2>
                 <br/>
                 
                <table className = "tabelarezultatkupovina">
                <tr>
                    <td>User : </td>
                    <td>{user.username}</td>
                </tr>

                <tr>
                    <td>Kupovina ID : </td>
                    <td>{kupovina.id}</td>
                </tr>
                <tr>
                    <td>Sifra kupovine : </td>
                    <td>{kupovina.sifra}</td>
                </tr>

                </table>
			<br/>
			<br/>
                 <h3>Kupljene stavke:</h3>
            
                <form>   
                        <table className = "tabelarezultatkupovinastavke">

                            <thead>
                                <tr>
                                    <th>ID</th>
									<th>Proizvod</th>
									<th>Cena Proizvoda</th>
									<th>Komada</th>
									<th>Cena Stavke</th>
                                </tr>
                            </thead>
                            <tbody>
                                { this.state.stavke.map(
                                        stavka => stavka.cenastavke >0 &&
                                        <tr key = {stavka.id}>
                                             <td> {stavka.id} </td> 
                                             <td> {stavka.proizvodNaziv} </td>   
                                             <td> {stavka.proizvodCena}</td>
                                             <td> {stavka.kolicinastavke}</td>
                                             <td> {stavka.cenastavke}</td>
                                        </tr> 
                                    )}

                            </tbody>
                        </table>
 					</form> 
				<br/>
				<br/>
                	<h2>Ukupna cena kupovine : &nbsp;&nbsp; &nbsp;&nbsp; {kupovina.ukupnacena} dinara</h2>
				<br/>
					<h6>Datum i vreme kupovine: &nbsp;&nbsp; &nbsp; {kupovina.datetime} </h6>
                 </div>
              
        )
    }
}

export default RezultatKupovineComponent
 