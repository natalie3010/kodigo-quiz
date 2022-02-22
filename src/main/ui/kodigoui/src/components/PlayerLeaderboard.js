import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Link } from 'react-router-dom';
import Navbarpool from "./Navbarpool";
import './Listing.css';

export default function PlayerLeaderboard() {

    const [users, setUsers] = useState([])
    const { qgid } = useParams();
    // const qgid = 2;


    useEffect(() => {
        getPlayers();
    }, [])

    function getPlayers() {
        fetch(`/player/${qgid}`)
            .then(response => {
                return response.json()
            })
            .then(data => {
                setUsers(data)
                console.log(data)

            })
    }




    return (
        <div>

            <Navbarpool />

            <div className='pool-container'>
                <h2>Player Leaderboard</h2>

                <table className='pool-table'>
                    <thead>
                        <tr>
                            <th>Ranking</th>
                            <th>Name</th>
                            <th>Score</th>
                        </tr>
                    </thead>
                    <tbody>

                        {users.map(user => (

                            <tr key={user.id}>
                                <td className='pool-actions'>{user.id}</td>
                                <td className='pool-name'>{user.playername}</td>
                                <td className='pool-actions'>{user.score}</td>
                            </tr>
                        ))}



                    </tbody>
                </table>
                <p className='admin--signuptext'>
                          Want to run your own quiz? Become an admin... <Link to='/adminsignup'>Sign up</Link>
                      </p>
            </div>



        </div>


    )
}