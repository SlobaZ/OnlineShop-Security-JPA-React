import React, { Component } from 'react'
import ProizvodiService from '../../services/ProizvodiService';

class CreateProizvodComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            naziv: '',
            marka: '',
            kolicina: '',
            cena: '',
            photo: ''
        }
        this.changeNazivHandler = this.changeNazivHandler.bind(this);
        this.changeMarkaHandler = this.changeMarkaHandler.bind(this);
        this.changeKolicinaHandler = this.changeKolicinaHandler.bind(this);
        this.changeCenaHandler = this.changeCenaHandler.bind(this);
        this.saveOrUpdateProizvod = this.saveOrUpdateProizvod.bind(this);
    }


    componentDidMount(){

        if(this.state.id === '_add'){
            return
        }else{
            ProizvodiService.getProizvodById(this.state.id).then( (res) =>{
                let proizvod = res.data;
                this.setState({naziv: proizvod.naziv,
                    marka: proizvod.marka,
                    kolicina: proizvod.kolicina,
                    cena : proizvod.cena,
                    photo: proizvod.photo
                });
            });
        }        
    }
    saveOrUpdateProizvod = (e) => {
        e.preventDefault();
        let proizvod = {naziv: this.state.naziv, marka: this.state.marka, kolicina: this.state.kolicina, 
                        cena: this.state.cena, photo: this.state.photo};                        
        console.log('proizvod => ' + JSON.stringify(proizvod));
        if(this.state.id === '_add'){
            ProizvodiService.createProizvod(proizvod).then(res =>{
                this.props.history.push('/proizvodi');
            });
        }else{
            ProizvodiService.updateProizvod(proizvod, this.state.id).then( res => {
                this.props.history.push('/proizvodi');
            });
        }
    }
    
    changeNazivHandler= (event) => {
        this.setState({naziv: event.target.value});
    }

    changeMarkaHandler= (event) => {
        this.setState({marka: event.target.value});
    }

    changeKolicinaHandler= (event) => {
        this.setState({kolicina: event.target.value});
    }

    changeCenaHandler= (event) => {
        this.setState({cena: event.target.value});
    }

    changePhotoHandler= (event) => {
        this.setState({photo: event.target.files[0].name});
    }

    cancel(){
        this.props.history.push('/proizvodi');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Proizvod</h3>
        }else{
            return <h3 className="text-center">Update Proizvod</h3>
        }
    }
    render() {
        return (
            <div>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Naziv: </label>
                                            <input placeholder="Naziv" name="naziv" className="form-control" 
                                                value={this.state.naziv} onChange={this.changeNazivHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Marka: </label>
                                            <input placeholder="Marka" name="marka" className="form-control" 
                                                value={this.state.marka} onChange={this.changeMarkaHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Kolicina: </label>
                                            <input placeholder="Kolicina" name="kolicina" className="form-control" 
                                                value={this.state.kolicina} onChange={this.changeKolicinaHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Cena: </label>
                                            <input placeholder="Cena" name="cena" className="form-control" 
                                                value={this.state.cena} onChange={this.changeCenaHandler}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> Photo: </label>
                                            <input type="file" name="photo"  className="form-control" 
                                                 onChange={this.changePhotoHandler}/>
                                        </div>

                                        <button className="btn btn-success" onClick={this.saveOrUpdateProizvod}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default CreateProizvodComponent
