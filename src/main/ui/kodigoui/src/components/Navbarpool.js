import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import './Navbarpool.css';

export default function Navbarpool() {

  return (

        <nav className='navbar--pool'>
          <Link to='/' className='logo'>Kodigo</Link>
            
            {/* <div className='container--buttons'>
                <button>Create Pool</button>

            </div> */}
           
        </nav>
     
  )
}