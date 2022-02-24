import React, {useState} from 'react';
import Navbar from './Navbar';
import './Home.css';

const AdminSignup = props => {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [pin, setPin] = useState("");

    async function SignUp() {
        let item = {name, email, pin}
        console.warn(item)
        

        let result =  await fetch('/admin/register', {
            method:'POST',
            body: JSON.stringify(item),
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Access-Control-Allow-Origin':'*'
            }

        })
        result = await result.json()
        console.warn(result)
    }



  return (
      <div>
          <Navbar />
          <div className='content'>
            <section className='admin'>
            <h1 className='admin--title'>Admin Sign up</h1> <br />
            <form className='login'>
                <label>Name</label> <br />
                <input className='admin--textfield' type="text" value={name} onChange={(e) => setName(e.target.value)} /> <br />
                <label>Email</label> <br />
                <input className='admin--textfield' type="email" value={email} onChange={(e) => setEmail(e.target.value)} /> <br />
                <label>Pin</label> <br />
                <input className='admin--textfield' type="text" value={pin} onChange={(e) => setPin(e.target.value)} /> <br />
                <button className='admin--button' onClick={SignUp} >Sign up</button>
            </form>
        </section>
      </div>
      </div>
      
  )
}

export default AdminSignup;
