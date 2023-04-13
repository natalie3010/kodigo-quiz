import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';
import logo from "./img/logo.svg";

export default function Navbar() {
  return (
    <div className='nav'>
      <div className='n-logo'>
    
        <Link to='/'><img src={logo} width="150" height="50"
        /></Link>
      </div>
    </div>

  )
}
