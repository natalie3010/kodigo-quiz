import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Navbarpool from "./Navbarpool";
import { useNavigate } from "react-router-dom";
import "./LeaderBListing.css";
import { Link } from "react-router-dom";

export default function Leaderboard() {

  const [users, setUsers] = useState([])
  const { qgid } = useParams();
  let history = useNavigate();

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

  function deleteAnswers() {
    alert(qgid);
    fetch(`/delete/player/${qgid}`, {
      method: "DELETE",
    }).then((result) => {
      result.json().then((resp) => {
        console.warn(resp);
        getPlayers();
      });
    });
  }


  return (
    <div>
      <Navbarpool />
      <div className="pool-container">
      <button onClick={() => history(-1)} className="L-back"> &#10094; </button>
        <h2> Leaderboard</h2>
        <hr className="pool-hr-line" />
        <table className="pool-table">
          
          <tbody>
            <td className="pool-name"> Name</td>
            <td className="pool-name"> Score</td>
            <td className="pool-name">
              
                <button className="pool-actions-leader" onClick={() => deleteAnswers()}>
                  {" "}
                  Delete leaderboard{" "}
                </button>
              
            </td>
          </tbody>
          <tbody>
            {users.map(user => (

              <tr key={user.id}>
                <td className='pool-actions'>{user.playername}</td>
                <td className='pool-name'>{user.score}</td>
                <td className='pool-actions'>
                  <Link to={"/group/leaderboard/list/" + qgid + "/" + user.playername}>
                    <button className="pool-actions-leader">
                      {" "}
                      View Breakdown{" "}
                    </button>
                  </Link></td>
              </tr>
            ))}



          </tbody>
        </table>
      </div>
    </div>
  );
}