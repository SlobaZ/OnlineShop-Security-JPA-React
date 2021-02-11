import React, { Component } from 'react'
import KupovineService from '../../services/KupovineService';
import UsersService from '../../services/UsersService';

class CreateKupovinaComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            users: [],
            id: this.props.match.params.id,
            sifra: '',
            ukupnacena: '',
            datetime: '',
            userId: ''
        }
        this.changeSifraHandler = this.changeSifraHandler.bind(this);
        this.changeUkupnacenaHandler = this.changeUkupnacenaHandler.bind(this);
        this.changeDatetimeHandler = this.changeDatetimeHandler.bind(this); 
        this.changeUserIdHandler = this.changeUserIdHandler.bind(this);
        this.saveOrUpdateKupovina = this.saveOrUpdateKupovina.bind(this);
    }


    componentDidMount(){

        if(this.state.id === '_add'){
            UsersService.getAlls().then((response) => {
                this.setState({ users: response.data });
              });
        }else{
            UsersService.getAlls().then((response) => {
                this.setState({ users: response.data });
              });
            KupovineService.getKupovinaById(this.state.id).then( (res) =>{
                let kupovina = res.data;
                this.setState({sifra: kupovina.sifra,
                    ukupnacena: kupovina.ukupnacena,
                    datetime : kupovina.datetime,
                    userId : kupovina.userId
                    
                });
            });
        }        
    }
    saveOrUpdateKupovina = (e) => {
        e.preventDefault();
        let kupovina = {sifra: this.state.sifra, ukupnacena: this.state.ukupnacena, datetime: this.state.datetime, userId: this.state.userId};
        console.log('kupovina => ' + JSON.stringify(kupovina));

        if(this.state.id === '_add'){
            KupovineService.createKupovina(kupovina).then(res =>{
                this.props.history.push('/kupovine');
            });
        }else{
            KupovineService.updateKupovina(kupovina, this.state.id).then( res => {
                this.props.history.push('/kupovine');
            });
        }
    }
    
    changeSifraHandler= (event) => {
        this.setState({sifra: event.target.value});
    }

    changeUkupnacenaHandler= (event) => {
        this.setState({ukupnacena: event.target.value});
    }

    changeDatetimeHandler= (event) => {
        this.setState({datetime: event.target.value});
    }
    
    changeUserIdHandler= (event) => {
        this.setState({userId: event.target.value});
    }

    cancel(){
        this.props.history.push('/kupovine');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Kupovina</h3>
        }else{
            return <h3 className="text-center">Update Kupovina</h3>
        }
    }
    getUkupnaCena(){
        if(this.state.id === '_add'){
            return 
        }else{
            return <div className = "form-group">
                        <label> Ukupna Cena: </label>
                            <input placeholder="Ukupnacena" name="ukupnacena" className="form-control" 
                            value={this.state.ukupnacena} onChange={this.changeUkupnacenaHandler}/>
                    </div>
        }
    }
    getDatetime(){
        if(this.state.id === '_add'){
            return 
        }else{
            return <div className = "form-group">
                        <label> Date and Time: </label>
                        <input placeholder="DateTime" name="datetime" className="form-control" 
                        value={this.state.datetime} onChange={this.changeDatetimeHandler}/>
                    </div>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Sifra: </label>
                                            <input placeholder="Sifra" name="sifra" className="form-control" 
                                                value={this.state.sifra} onChange={this.changeSifraHandler}/>
                                        </div>
                                        {
                                            this.getUkupnaCena()
                                        }
                                        {
                                            this.getDatetime()
                                        }
                                        <div className = "form-group">
                                            <label> User: </label>
                                            <select name="userId" className="form-control" 
                                            value={this.state.userId} onChange={this.changeUserIdHandler}> 
                                            <option value={''}> --- Odaberi ---</option>  
                                            {this.state.users.map(user => (
                                            <option value={user.id}>{user.username}</option> ))}
                                            </select>
                                        </div>

                                        <button className="btn btn-success" onClick={this.saveOrUpdateKupovina}>Save</button>
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

export default CreateKupovinaComponent
