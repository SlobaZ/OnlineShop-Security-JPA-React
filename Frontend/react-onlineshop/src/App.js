import React, { Component } from "react";
import { HashRouter,Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import logo from './logo.jpg';

import AuthService from "./services/auth.service";

import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/home.component";
import ListProizvodiComponent from "./components/proizvodi/ListProizvodiComponent.jsx";
import CreateProizvodComponent from "./components/proizvodi/CreateProizvodComponent.jsx";
import Profile from "./components/profile.component";
import ListUsersComponent from "./components/users/ListUsersComponent.jsx";
import CreateUserComponent from "./components/users/CreateUserComponent.jsx";
import ListKupovineComponent from "./components/kupovine/ListKupovineComponent.jsx";
import CreateKupovinaComponent from "./components/kupovine/CreateKupovinaComponent.jsx";
import KreirajKupovinuComponent from "./components/stavke/KreirajKupovinuComponent.jsx"
import ListStavkeComponent from "./components/stavke/ListStavkeComponent.jsx";

class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      showAdmin: false,
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
        showAdmin: user.roles.includes("ROLE_ADMIN"),
      });
    }
  }

  logOut() {
    AuthService.logout();
  }

  render() {
    const { currentUser, showAdmin} = this.state;

    return (
      <div>      
      
      <nav className="navbar navbar-expand-md navbar-dark bg-dark">
        
        <div className="logo">
        <img src={logo} width="50" height="50" alt="Logo" />
        </div>
        
          <Link to={"/"} className="navbar-brand">
            OnlineShop
          </Link>
          <div className="navbar-nav mr-auto ml-4">
           
            <li className="nav-item ml-2">
              <Link to={"/proizvodi"} className="nav-link">
                Proizvodi
              </Link>
            </li>

            <li className="nav-item ml-2">
              <Link to={"/kupi"} className="nav-link">
                Kupi
              </Link>
            </li>
            
            {showAdmin && (
            <li className="nav-item ml-2">
              <Link to={"/kupovine"} className="nav-link">
                Kupovine
              </Link>
            </li>
            )}
            
            {showAdmin && (
              <li className="nav-item ml-3">
                <Link to={"/users"} className="nav-link">
                  Users
                </Link>
              </li>
            )}


          </div>
          {currentUser ? (
            <div className="navbar-nav ml-auto mr-4">
             
              <li className="nav-item mr-4">
                <Link to={"/profile"} className="nav-link">
                  {currentUser.username}
                </Link>
              </li>
              <li className="nav-item mr-1">
                <a href="/" className="nav-link" onClick={this.logOut}>
                  LogOut
                </a>
              </li>
            </div>
          ) : (
            <div className="navbar-nav ml-auto mr-4" >
              <li className="nav-item mr-4">
                <Link to={"/login"} className="nav-link">
                  Login
                </Link>
              </li>

              <li className="nav-item mr-1">
                <Link to={"/register"} className="nav-link">
                  Sign Up
                </Link>
              </li>
            </div>
          )}
        </nav>

       <div className="container mt-3">
		<HashRouter>
          <Switch>
            <Route exact path="/" component={Home} />
            <Route exact path="/proizvodi" component={ListProizvodiComponent} />" +
            <Route exact path="/addorupdate-proizvod/:id" component={CreateProizvodComponent} />
            <Route exact path="/users" component={ListUsersComponent} />
	          <Route exact path="/update-user/:id" component={CreateUserComponent} />
			  <Route exact path="/kupovine" component={ListKupovineComponent} />
	          <Route exact path="/update-kupovina/:id" component={CreateKupovinaComponent} />
			  <Route exact path="/kupi" component={KreirajKupovinuComponent} />
            <Route exact path="/zapocnikupovinu/:id" component={ListStavkeComponent} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/profile" component={Profile} />
          </Switch>
		</HashRouter>
        </div>
      </div>
    );
  }
}

export default App;
