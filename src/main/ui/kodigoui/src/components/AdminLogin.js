import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Alert } from 'react-bootstrap';
import { authenticateAdmin } from '../services/authentication/authActions';
import Navbar from './Navbar';
import './Home.css';



class AdminLogin extends Component {

  constructor(props){
    super(props);
    this.state = this.initialState;
  }

  initialState = {
    username:'', pin:'', error:''
  }

  credentialChange = event => {
    this.setState({
      [event.target.name] : event.target.value
    })
  }
  
  validateAdmin = () => {
    this.props.authenticateAdmin(this.state.username, this.state.pin);
    setTimeout(() => {
      if(this.props.authReducer.isLoggedIn) {
        return this.props.history.push('/navbarpool');
      } else {
        this.setState({"error":"Invalid username and/or password"});
      }
    }, 500);
  }

  render() {
    const {username, pin, error} = this.state;

    return (
          <div> 
              <Navbar />
              <div className='content'>
              <section className='admin'>
                {error && <Alert variant="danger">{error}</Alert>}
                  <h1 className='admin--title'>Admin Log in</h1> <br />
                  <form className='login'>
                      <label>Username</label> <br />
                      <input className='admin--textfield' required autoComplete='off' type='text' name='username' value={username} onChange={this.credentialChange}  /> <br />
                      <label>Existing pin</label> <br />
                      <input className='admin--textfield' type='password' name='pin' value={pin} onChange={this.credentialChange} /> <br />
                      
                      {/* When page loads Login button will be disabled  */}
                      <Link to={'/adminwelcome/' + pin}>
                          <button className='admin--button' type='submit' onClick={this.validateAdmin}
                            disabled={this.state.username.length === 0 || this.state.pin.length === 0}>
                              Log in
                          </button>
                      </Link>
                      

                      <p className='admin--signuptext'>
                          Don't have an account? <Link to='/adminsignup'>Sign up</Link>
                      </p>
                  </form>
              </section>
              </div>
          </div>
    )
  }
}

const mapStateToProps = state => {
  return {
    authReducer:state.authReducer
  }
}

const mapDispatchToProps = dispatch => {
  return {
    authenticateAdmin: (username, pin) => dispatch(authenticateAdmin(username, pin))
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(AdminLogin);