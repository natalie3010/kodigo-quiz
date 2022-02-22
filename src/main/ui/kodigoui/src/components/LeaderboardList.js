import React, { useEffect, useState } from "react";
import Navbarpool from "./Navbarpool";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import "./LeaderBListing.css";
import { Link } from "react-router-dom";


export default function LeaderboardList() {

  const { qgid } = useParams();
  const { playername } = useParams();
  let history = useNavigate();

  const [question, setQuestion] = useState([]);
  const [users, setUsers] = useState([]);
  const [playerName, setPlayerName] = useState([]);

  useEffect(() => {
    getQuest();
  }, []);

  function getQuest() {
    fetch(`/player/question/${qgid}`)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setQuestion(data);
        console.log(data);
        
      });
  }


  useEffect(() => {
    getPlayers();
  }, []);

  function getPlayers() {
    fetch(`/player/answer/${qgid}/${playername}`)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setUsers(data);
        console.log(data);
        
      });
  }

  useEffect(() => {
    getPlayername();
}, [])

function getPlayername() {
    fetch(`/player/${qgid}`)
        .then(response => {
            return response.json()
        })
        .then(data => {
          setPlayerName(data)
            console.log(data)

        })
}




  return (
    <div>
      <Navbarpool />
      <section className="pool-containerq">
        
        <div>
        <button onClick={() => history(-1)} className="L-back"> &#10094; </button>
        
        <h2 className="L-name">{playername}</h2>
        </div>
        
        {/*Drop down name */}
        {/* <select name="students">
        
        {playerName.map((pname) => (
                <option key={pname.id}>
                  <option>{pname.playername}</option>
                  
                </option>
              ))}
          

          <option value="first">Tom</option>
          <option value="second" selected>
            Jack
          </option>
          <option value="third">Harry</option>
        </select> */}

        {/*Q&A Breakdown */}
        <hr className="pool-hr-lineq" />
        
        <div class="main">
        <div class="table1">

        <table>
          <thead>
            <td className="name-label">Question</td>
            {/* <td className="name-label">Correct Answer</td> */}
          </thead>

          <tbody>
            <tr>
              {question.map((quest) => (
                <tr key={quest.qid} key={users.qgid}>
                  <td className="pool-name">{quest.question}</td>
                  {/* <td className="pool-name">{quest.correct}</td> */}
                </tr>
              ))}
            </tr>
          </tbody>
        </table>
        
        </div>
        <div class="table2">

        <table>
          <thead>
            {/* <td className="name-label">Question</td> */}
            <td className="name-label">Correct Answer</td>
          </thead>

          <tbody>
            <tr>
              {question.map((quest) => (
                <tr key={quest.qid} key={users.qgid}>
                  {/* <td className="pool-name">{quest.question}</td> */}
                  <td className="pool-name">{quest.correct}</td>
                </tr>
              ))}
            </tr>
          </tbody>
        </table>
        
        </div>
        

        <div class="table3">

        <table>
          <thead>
            <td className="actions-label">Answer Choice</td>
          </thead>

          <tbody>
            {users.map((user) => (
              <tr key={user.qgid}>
                <td className="pool-name">{user.answer}</td>
              </tr>
            ))}
          </tbody>

        </table>
        </div>
        </div>
        
      </section>
    </div>
  );
}
