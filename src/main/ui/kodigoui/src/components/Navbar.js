import React from 'react';
import { Link } from 'react-router-dom';

export default function Navbar() {
  return (
    <nav>
        <Link to='/' className='logo'>Kodigo</Link>
    </nav>
  )
}
