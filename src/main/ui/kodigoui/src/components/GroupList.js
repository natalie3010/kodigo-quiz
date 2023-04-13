import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";
import Navbarpool from "./Navbarpool";
import { useNavigate } from "react-router-dom";
import { Modal, Button } from "react-bootstrap";
import "./Listing.css";

export default function GroupList() {
  const [users, setUsers] = useState([]);
  const { qpid } = useParams();
  let history = useNavigate();

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [name, setName] = useState("");

  async function SignUp() {
    let item = { name };
    console.warn(item);

    let result = await fetch(`/add/group/${qpid}`, {
      method: "POST",
      body: JSON.stringify(item),
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        "Access-Control-Allow-Origin": "*",
      },
    });
    result = await result.json();
    console.warn(result);
  }

  useEffect(() => {
    getGroup();
  }, []);

  function getGroup() {
    fetch(`/group/${qpid}`)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setUsers(data);
        setName(data[0].name);
      });
  }

  function deleteGroup(qgid) {
    alert("Refresh the page folks");
    fetch(`/delete/group/${qgid}`, {
      method: "DELETE",
    }).then((result) => {
      result.json().then((resp) => {
        console.warn(resp);
        getGroup();
      });
    });
  }

  const [showEdit, setShowEdit] = useState(false);
  const handleCloseEdit = () => setShowEdit(false);

  const handleShowEdit = (qgid) => {
    setShowEdit(true);

    selectUser(qgid);
    console.log(qgid);
  };

  const [qgid, setQgid] = useState(0);
  const [getOne, setGetOne] = useState();

  function selectUser(qgid) {
    fetch(`/findgroup/${qgid}`)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setGetOne(data);
        console.log(data);
        setName(data.name);
        setQgid(data.qgid);
        console.log(name);
        console.log(qgid);
      });
  }

  function Edit() {
    let item = { name };
    console.warn("item", item);

    fetch(`/edit/group/${qgid}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        // 'Access-Control-Allow-Origin':'*'
      },
      body: JSON.stringify(item),
    }).then((result) => {
      result.json().then((resp) => {
        console.warn(resp);
        getGroup();
      });
    });
  }

  return (
    <div>
      <Navbarpool />

      <div className="pool-container">
        <button onClick={() => history(-1)} className="L-back">
          {" "}
          &#10094;{" "}
        </button>
        <h2>Group list from pool</h2>
        <div className="t-responsive">
          <table className="pool-table">
            <thead>
              <tr>
                <th className="pin-label">Quiz PIN</th>
                <th className="name-label">Name</th>
                <th className="actions-label">Actions</th>
              </tr>
            </thead>
            <tbody>
              {users.map((user) => (
                <tr key={user.qgid}>
                  <td className="pool-pin">{user.qgid}</td>
                  <td className="pool-name">{user.name}</td>
                  <td className="pool-actions">
                    <Link to={"/questions/" + user.qgid}>
                      <button>Enter group</button>
                    </Link>
                    <Link to={"/group/leaderboard/" + user.qgid}>
                      <button>Leaderboard</button>
                    </Link>
                    <button onClick={() => handleShowEdit(user.qgid)}>
                      Edit
                    </button>
                    <button onClick={() => deleteGroup(user.qgid)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}

              <>
                <Modal show={show} onHide={handleClose}>
                  <Modal.Header>
                    <Modal.Title>Add a new group here!</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>
                    <form className="login">
                      <label>Group Name</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                      />{" "}
                      <br />
                      <button className="admin--button" onClick={SignUp}>
                        Add Group
                      </button>
                    </form>
                  </Modal.Body>
                  <Modal.Footer>
                    <button className="secondary" onClick={handleClose}>
                      Close
                    </button>
                  </Modal.Footer>
                </Modal>
              </>

              <>
                <Modal show={showEdit} onHide={handleCloseEdit}>
                  <Modal.Body>
                    <form className="login">
                      <label>Edit Group Name</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                      />{" "}
                      <br />
                      <button className="admin--button" onClick={Edit}>
                        Submit change
                      </button>
                    </form>
                  </Modal.Body>
                  <Modal.Footer>
                    <button className="secondary" onClick={handleCloseEdit}>
                      Close
                    </button>
                  </Modal.Footer>
                </Modal>
              </>
            </tbody>
          </table>
        </div>
        <div className="container--buttons">
          <button className="secondary" onClick={handleShow}>
            Create Group
          </button>
        </div>
      </div>
    </div>
  );
}
