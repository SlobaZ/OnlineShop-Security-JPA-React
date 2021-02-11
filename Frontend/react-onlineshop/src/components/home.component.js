import React, { Component } from "react";

import AuthService from "../services/auth.service";

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {  
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

  render() {
    const { currentUser, showAdmin } = this.state;
    return (
      <div className="container">
        <header className="jumbotron">
          <h3>Home Page</h3>
        </header>
        <br/>
        {currentUser && (
              <h4>Ovo moze da vidi samo User</h4>
            )}
        <br/>    
        {showAdmin && (
              <h4>Ovo moze da vidi samo Administrator</h4>
            )}

      </div>
    );
  }
}
