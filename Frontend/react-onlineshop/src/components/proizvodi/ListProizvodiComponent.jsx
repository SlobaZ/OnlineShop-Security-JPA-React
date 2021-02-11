import React, { Component } from 'react'
import ProizvodiService from '../../services/ProizvodiService'
import AuthService from "../../services/auth.service";

class ListProizvodiComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                proizvodi: [],
                kategorije: [],
                searchNaziv: '',
                searchMarka: '',
                searchKategorija: '',
                searchCena: ''
                
        };
        
        this.addProizvod = this.addProizvod.bind(this);
        this.editProizvod = this.editProizvod.bind(this);
        this.deleteProizvod = this.deleteProizvod.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        
    }

    handleChange(event) {
       this.setState({[event.target.name]: event.target.value});  
    }
    
    handleSubmit(event) {
        event.preventDefault();  
       this.refreshProizvodi();
    }

    deleteProizvod(id){
        ProizvodiService.deleteProizvod(id).then( res => {
            this.setState({proizvodi: this.state.proizvodi.filter(proizvod => proizvod.id !== id)});
        });
    }

    editProizvod(id){
        this.props.history.push(`/addorupdate-proizvod/${id}`);
    }

    componentDidMount(){
        this.refreshProizvodi();
    }

    refreshProizvodi() {
        const user = AuthService.getCurrentUser();
    if (user) {
      this.setState({
        currentUser: user,
        showModerator: user.roles.includes("ROLE_MODERATOR"),
        showAdmin: user.roles.includes("ROLE_ADMIN"),
      });
    }
        let config = { params: {} };
    
        if (this.state.naziv !== "") {
          config.params.naziv = this.state.searchNaziv;
        }
        if (this.state.marka !== "") {
          config.params.marka = this.state.searchMarka;
        }
        if (this.state.kategorija !== "") {
            config.params.kategorija = this.state.searchKategorija;
          }
        if (this.state.cena !== "") {
          config.params.cena = this.state.searchCena;
        }
        ProizvodiService.getKategorije().then((response) => {
            this.setState({ kategorije: response.data });
          });
        ProizvodiService.getProizvodi(config).then((response) => {
          this.setState({ proizvodi: response.data });
        });
      }

    addProizvod(){
        this.props.history.push('/addorupdate-proizvod/_add');
    }

    render() {
        const { showAdmin } = this.state;
        return (
            <div>
                <br/>
                 <div className="pretraga">
                <form onSubmit={this.handleSubmit}>
                   
                    <div className="form-group">
                    <label className="form-control">  Naziv: 
                    <input type="text" name="searchNaziv" value={this.state.searchNaziv} onChange={this.handleChange}/>
                    </label>
                    </div>

                    <div className="form-group">
                    <label className="form-control">  Marka: 
                    <input type="text" name="searchMarka" value={this.state.searchMarka} onChange={this.handleChange}/> 
                    </label>
                    </div>

                    <div className="form-group">
                    <label className="form-control">  Kategorija: 
                    <select name="searchKategorija" value={this.state.searchKategorija} onChange={this.handleChange}> 
                            <option value={''}> --- Odaberi ---</option>  
                            {this.state.kategorije.map(kategorija => (
                            <option value={kategorija}>{kategorija}</option> ))}
                    </select>
                    </label>
                    </div>

                    <div className="form-group">
                    <label className="form-control">  Cena: 
                    <input type="text" name="searchCena" value={this.state.searchCena} onChange={this.handleChange}/> 
                    </label>
                    </div>

                    <div className="form-group">
                    <input type="submit" value="Search" />
                    </div>

                </form>
                </div>

                 <h2 className="text-center">Proizvod List</h2>

                 {showAdmin && (
                 <div className = "row">
                    <button className="btn btn-primary" onClick={this.addProizvod}> Add Proizvod</button>
                 </div>
                  )}
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Naziv</th>
                                    <th> Marka</th>
                                    <th> Photo</th>
                                    <th> Kolicina</th>
                                    <th> Cena</th>
                                    <th> Kategorija</th>
                                    {showAdmin && (    <th> Actions</th>  )}
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.proizvodi.map(
                                        proizvod => 
                                        <tr key = {proizvod.id}>
                                             <td> {proizvod.naziv} </td>   
                                             <td> {proizvod.marka} </td> 
                                             <td><img src={process.env.PUBLIC_URL + '/images/' + proizvod.photo} alt="slika" width="65" height="65" /></td>
                                             <td> {proizvod.kolicina}</td>
                                             <td> {proizvod.cena}</td>
                                             <td> {proizvod.kategorija}</td>
                                             {showAdmin && ( 
                                             <td>
                                                 <button onClick={ () => this.editProizvod(proizvod.id)} className="btn btn-info">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteProizvod(proizvod.id)} className="btn btn-danger">Delete </button>
                                             </td>
                                             )}
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default ListProizvodiComponent
