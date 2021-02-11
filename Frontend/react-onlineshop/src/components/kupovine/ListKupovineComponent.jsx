import React, { Component } from 'react'
import KupovineService from '../../services/KupovineService'
import UsersService from '../../services/UsersService'
import AuthService from "../../services/auth.service";

class ListKupovineComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                kupovine: [],
                users: [],
                searchUserId: '',
                searchSifra: '',
                searchUkupnaCena: '',
                searchDatumvremePocetak : '',
                searchDatumvremeKraj: ''
        };
        
        this.editKupovina = this.editKupovina.bind(this);
        this.deleteKupovina = this.deleteKupovina.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        
    }

    handleChange(event) {
       this.setState({[event.target.name]: event.target.value});  
    }
    
    handleSubmit(event) {
        event.preventDefault();  
       this.refreshKupovine();
    }

    deleteKupovina(id){
        KupovineService.deleteKupovina(id).then( res => {
            this.setState({kupovine: this.state.kupovine.filter(kupovina => kupovina.id !== id)});
        });
    }

    editKupovina(id){
        this.props.history.push(`/update-kupovina/${id}`);
    }

    componentDidMount(){
        this.refreshKupovine();
    }

    refreshKupovine() {
       const user = AuthService.getCurrentUser();
    if (user) {
      this.setState({
        currentUser: user,
        showModerator: user.roles.includes("ROLE_MODERATOR"),
        showAdmin: user.roles.includes("ROLE_ADMIN"),
      });
    }
        let config = { params: {} };
    
        if (this.state.userId !== "") {
          config.params.userId = this.state.searchUserId;
        }
        if (this.state.sifra !== "") {
          config.params.sifra = this.state.searchSifra;
        }
        if (this.state.ukupnaCena !== "") {
          config.params.ukupnaCena = this.state.searchUkupnaCena;
        }
         if (this.state.datumvremePocetak !== "") {
          config.params.datumvremePocetak = this.state.searchDatumvremePocetak;
        }
         if (this.state.datumvremeKraj !== "") {
          config.params.datumvremeKraj = this.state.searchDatumvremeKraj;
        }
        UsersService.getAlls().then((response) => {
            this.setState({ users: response.data });
          });
        KupovineService.getKupovine(config).then((response) => {
          this.setState({ kupovine: response.data });
        });
      }


    render() {
    const { showAdmin } = this.state;
        return (
            <div>
                <br/>
                {showAdmin && (
                 <div className="pretragaKupovina">
                <form onSubmit={this.handleSubmit}>
                   
                    <div className="form-group">
                    <label className="form-control">  User: 
                    <select name="searchUserId" value={this.state.searchUserId} onChange={this.handleChange}> 
                            <option value={''}> --- Odaberi ---</option>  
                            {this.state.users.map(user => (
                            <option value={user.id}>{user.username}</option> ))}
                    </select>
                    </label>
                    </div>

                    <div className="form-group">
                    <label className="form-control">  Sifra: 
                    <input type="text" name="searchSifra" value={this.state.searchSifra} onChange={this.handleChange}/> 
                    </label>
                    </div>

                    <div className="form-group">
                    <label className="form-control"> Ukupna Cena: 
                    <input type="text" name="searchUkupnaCena" value={this.state.searchUkupnaCena} onChange={this.handleChange}/> 
                    </label>
                    </div>
                    
                    <div className="form-group">
                    <label className="form-control"> Datum i Vreme Pocetak: 
                    <input type="text" name="searchDatumvremePocetak" value={this.state.searchDatumvremePocetak} onChange={this.handleChange}/> 
                    </label>
                    </div>
                    
                    <div className="form-group">
                    <label className="form-control"> Datum i Vreme Kraj: 
                    <input type="text" name="searchDatumvremeKraj" value={this.state.searchDatumvremeKraj} onChange={this.handleChange}/> 
                    </label>
                    </div>

                    <div className="form-group">
                    <input type="submit" value="Search" />
                    </div>

                </form>
                </div>
				)}
                 <h2 className="text-center">Kupovina List</h2>
                 <br></br>
                 {showAdmin && (
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Sifra</th>
                                    <th> Kupac</th>
                                    <th> Datum i Vreme</th>
                                    <th> Ukupna cena</th>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.kupovine.map(
                                        kupovina => 
                                        <tr key = {kupovina.id}>
                                             <td> {kupovina.sifra} </td>   
                                             <td> {kupovina.userUsername} </td>
                                             <td> {kupovina.datetime}</td>
                                             <td> {kupovina.ukupnacena}</td>
                                             <td>
                                                 <button onClick={ () => this.editKupovina(kupovina.id)} className="btn btn-info">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteKupovina(kupovina.id)} className="btn btn-danger">Delete </button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>
				)}
            </div>
        )
    }
}

export default ListKupovineComponent
