import React, { Component } from 'react'
import KupovineService from '../../services/KupovineService'
import AuthService from "../../services/auth.service";

class KreirajKupovinuComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            user: {},
            kupovina: {},
        };
    }

    zapocniKupovinu(id){
        this.props.history.push(`/zapocnikupovinu/${id}`);
    }

    componentDidMount(){
        const user = AuthService.getCurrentUser();
        if (!user) {
            this.props.history.push('/login');
        }
        else{
            this.setState({
                user: AuthService.getCurrentUser()
              });
            const username = String(user.username);  
        KupovineService.createKupovina(username).then((response) => {
            this.setState({ kupovina: response.data });
          });
        }
    }


    render() {
        const { kupovina } = this.state;
        const { user } = this.state;
        return (
            <div>
                <br/>
                 <h2 className="text-center">Zapocni kupovinu</h2>
                 <br/>
                 <br/>

                <table className = "tabelazapocnikupovinu">
                <tr>
                    <td>User : </td>
                    <td>{user.username}</td>
                </tr>
                <tr>
                    <td> - </td>
                    <td> - </td>
                </tr>
                <tr>
                    <td>Kupovina ID : </td>
                    <td>{kupovina.id}</td>
                </tr>
                <tr>
                    <td>Sifra kupovine : </td>
                    <td>{kupovina.sifra}</td>
                </tr>
                <tr>
                    <td> - </td>
                    <td> - </td>
                </tr>
                <tr>
                    <td>Zapocni : </td>
                    <td><button onClick={ () => this.zapocniKupovinu(kupovina.id)} className="btn btn-primary" > Zapocni Kupovinu</button></td>
                </tr>
                </table>

                 

                 </div>
              
        )
    }
}

export default KreirajKupovinuComponent
