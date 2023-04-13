import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Navbarpool from "./Navbarpool";
import "./Listing.css";
import { Modal } from "react-bootstrap";

export default function PoolList() {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [users, setUsers] = useState([]);
  const { adid } = useParams();
  let history = useNavigate();

  const [name, setName] = useState("");

  async function SignUp() {
    let item = { name };
    console.warn(item);

    let result = await fetch(`/add/pool/${adid}`, {
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
    getPool();
  }, []);

  function getPool() {
    fetch(`/users/${adid}`)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setUsers(data);
        setName(data[0].name);
      });
  }

  function deletePool(qpid) {
    alert(qpid);
    fetch(`/delete/pool/${qpid}`, {
      method: "DELETE",
    }).then((result) => {
      result.json().then((resp) => {
        console.warn(resp);
        getPool();
      });
    });
  }

  const [showEdit, setShowEdit] = useState(false);
  const handleCloseEdit = () => setShowEdit(false);

  const handleShowEdit = (qpid) => {
    setShowEdit(true);

    selectUser(qpid);
    console.log(qpid);
  };

  const [qpid, setQpid] = useState(0);
  const [getOne, setGetOne] = useState();

  function selectUser(qpid) {
    fetch(`/findpool/${qpid}`)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setGetOne(data);
        console.log(data);
        setName(data.name);
        setQpid(data.qpid);
        console.log(name);
        console.log(qpid);
      });
  }

  function Edit() {
    let item = { name };
    console.warn("item", item);

    fetch(`/edit/pool/${qpid}`, {
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
        getPool();
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
        <h2>Pool list from Admin</h2>
        <div className="t-responsive">
          <table className="pool-table">
            <thead>
              <tr>
                <th className="name-label">Name</th>
                <th className="actions-label">Actions</th>
              </tr>
            </thead>
            <tbody>
              {users.map((user) => (
                <tr key={user.qpid}>
                  <td className="pool-name">{user.name}</td>
                  <td className="pool-actions">
                    <Link to={"/group/" + user.qpid}>
                      <button>Enter pool</button>
                    </Link>
                    <button onClick={() => handleShowEdit(user.qpid)}>
                      Edit
                    </button>
                    <button onClick={() => deletePool(user.qpid)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
              <>
                <Modal show={show} onHide={handleClose}>
                  <Modal.Header>
                    <Modal.Title>Add a new pool here!</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>
                    <form className="login">
                      <label>Pool Name</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                      />{" "}
                      <br />
                      <button className="admin--button" onClick={SignUp}>
                        Add Question
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
                      <label>Edit Pool Name</label> <br />
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
            Create Pool
          </button>
        </div>
      </div>
    </div>
  );
}
