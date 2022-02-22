import React, { useEffect, useState } from "react"
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import Navbar from './Navbar';
import './Listing.css'

function PoolList() {


    const [users, setUsers] = useState([])
    const { pin } = useParams();

    
  
    const fetchData = () => {
      fetch(`/admin/login/${pin}`)
        .then(response => {
          return response.json()
        })
        .then(data => {
          setUsers(data)
        })
    }
  
    useEffect(() => {
      fetchData()
    }, [])
    
  
    return (
        <div>
            <Navbar />

            <div className='pool-containerq'>
                <h2>Welcome to the admin page</h2>
                <hr className='pool-hr-line' />






                {users.map(user => (
                    <table className='pool-table'>

                        <tr key={user.qgid}>
                            <td><h2>Welcome, {user.name}</h2></td>
                        </tr>
                        <p>You will be able to acess the pools to create, edit and delete them. Once you have created a pool, you can then dive in and look at your groups. </p>
                        <p>Inside your groups, you can now create, edit and delete the groups that will hold your questions.</p>
                        <tr>
                            <td>
                                <div className = "pool-actions2">
                                <Link to={'/pool/'+ user.adid}>
                                    <button>Enter the view pools</button>
                                </Link>
                                </div>
                            </td>
                        </tr>



                    </table>
                ))}


            </div>
        </div>
    )


}

export default PoolList;