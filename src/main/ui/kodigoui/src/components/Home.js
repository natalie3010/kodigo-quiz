import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Home.css";
import { Modal, Button } from "react-bootstrap";
import Footer from "./Footer";

export default function Home() {
  const [name, setName] = useState("");

  function selectUser(pin) {
    fetch(`/findgroup/${user.pin}`)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        console.log(data);
        setName(data.name);
      });
  }

  let navigate = useNavigate();

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  
  const handleShow = (pin, username) => {
    setShow(true);
    selectUser(pin);
    console.log(pin);
    console.log(username);
  };
  
  const initialState = {
    username: "",
    pin: "",
  };

  const [user, setUser] = useState(initialState);

  const credentialChange = (event) => {
    const { name, value } = event.target;
    setUser({ ...user, [name]: value });
  };

  const validatePin = () => {
    if (user.pin !== null) {
      console.log("logged in");
      // return navigate('/play/' + user.pin)
      return navigate("/play/" + user.pin + '/' + user.username);
    } else {
      console.log("pin");
      navigate("/");
    }
  };
  


  return (
    <div>
      <header>
        <Link to="adminlogin">
          <button className="adminarea--button">Admin Area</button>
        </Link>
      </header>
      <h1 className="home--title">KODIGO</h1> <br />
      <section className="section--homeplay bounce-5">
        <div className="form--homepin">
          <input
            className="home--textfield"
            required
            autoComplete="off"
            type="text"
            name="username"
            placeholder="Enter Player Name"
            value={user.username}
            onChange={credentialChange}
          />{" "}
          <br />
          <input
            className="home--textfield"
            type="text"
            placeholder="Enter pin"
            name="pin"
            value={user.pin}
            onChange={credentialChange}
          />{" "}
          <br />
          <button className="home--button" onClick={handleShow}>
            Submit
          </button>
          
        </div>
      </section>
      <>
            <Modal
              show={show}
              onHide={handleClose}
              backdrop="static"
              keyboard={false}
              size="lg"
              aria-labelledby="contained-modal-title-vcenter"
              centered
            >
              <Modal.Body>
                {/* <div>
                    <section className="quiz-container"> */}
                <div className="buffer-message">
                  <div className="buffer-title">
                    <h1>The quiz is about to begin!</h1>
                  </div>
                  <p>
                    You have <b>5 seconds</b> to answer every question
                  </p>
                  <p>
                    There are 10 questions. Your score will be revealed and
                    recorded at the end.
                  </p>
                  <p>
                    This quiz is on{" "}
                    <i>
                      <b>{name}</b>.
                    </i>
                  </p>
                  <p>
                    Click <b>Start</b> to begin the quiz!
                  </p>
                </div>
                {/* </section> */}
                {/* </div> */}
              </Modal.Body>
              <Modal.Footer>
                <Button className="secondary" onClick={handleClose}>
                  Close
                </Button>
                {/* <Link to={'/play/' + user.pin +'/' + user.username}> */}
                  <Button className="primary" onClick={validatePin}>
                    Start
                  </Button>
                {/* </Link> */}
              </Modal.Footer>
            </Modal>
          </>
          <div class="animation-area">
            <ul class="box-area">
              <li></li>
              <li></li>
              <li></li>
              <li></li>
            </ul>
          </div>
          <Footer/>
    </div>
  );
}
