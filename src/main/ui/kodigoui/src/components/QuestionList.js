import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Navbarpool from "./Navbarpool";
import { useNavigate } from "react-router-dom";
import { Modal } from "react-bootstrap";
import "./QuestionListing.css";

export default function QuestionList() {
  const [users, setUsers] = useState([]);
  const { qgid } = useParams();
  let history = useNavigate();

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [question, setQuestion] = useState("");
  const [answer1, setAnswer1] = useState("");
  const [answer2, setAnswer2] = useState("");
  const [answer3, setAnswer3] = useState("");
  const [answer4, setAnswer4] = useState("");
  const [correct, setCorrect] = useState("");

  async function SignUp() {
    let item = { question, answer1, answer2, answer3, answer4, correct };
    console.warn(item);

    let result = await fetch(`/add/question/${qgid}`, {
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
    getQues();
  }, []);

  function getQues() {
    fetch(`/groups/${qgid}`)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setUsers(data);
      });
  }

  function deleteQues(qid) {
    alert(qid);
    fetch(`/delete/question/${qid}`, {
      method: "DELETE",
    }).then((result) => {
      result.json().then((resp) => {
        console.warn(resp);
        getQues();
      });
    });
  }

  const [showEdit, setShowEdit] = useState(false);
  const handleCloseEdit = () => setShowEdit(false);

  const handleShowEdit = (qid) => {
    setShowEdit(true);

    selectUser(qid);
    console.log(qid);
  };

  const [qid, setQid] = useState(0);
  const [getOne, setGetOne] = useState();

  function selectUser(qid) {
    fetch(`/findquestion/${qid}`)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setGetOne(data);
        console.log(data);
        setQuestion(data.question);
        setAnswer1(data.answer1);
        setAnswer2(data.answer2);
        setAnswer3(data.answer3);
        setAnswer4(data.answer4);
        setCorrect(data.correct);
        setQid(data.qid);
        console.log(question);
        console.log(answer1);
        console.log(qid);
      });
  }

  function Edit() {
    let item = { question, answer1, answer2, answer3, answer4, correct };
    console.warn("item", item);

    fetch(`/edit/question/${qid}`, {
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
        getQues();
      });
    });
  }

  return (
    <div>
      <Navbarpool />

      <div className="pool-containerq">
        <button onClick={() => history(-1)} className="L-back">
          {" "}
          &#10094;{" "}
        </button>
        <h2>Questions from the group</h2>
        <hr className="pool-hr-lineq" />
        <div className="t-responsive">
          <table className="pool-table">
            <thead>
              <th className="name-label">Question</th>
              <th className="answer-label">Answer 1</th>
              <th className="answer-label">Answer 2</th>
              <th className="answer-label">Answer 3</th>
              <th className="answer-label">Answer 4</th>
              <th className="answer-label">Correct</th>
              <th className="actions-label">Actions</th>
            </thead>
          </table>
        </div>
        <hr className="pool-hr-line" />
        <div className="t-responsive">
          <table className="pool-table">
            <tbody>
              {users.map((user) => (
                <tr key={user.qid}>
                  <td className="pool-name">{user.question}</td>
                  {/* <div className='answers-container'> */}
                  <td className="answer-option" margin="15px">
                    {user.answer1}
                  </td>
                  <td className="answer-option">{user.answer2}</td>
                  <td className="answer-option">{user.answer3}</td>
                  <td className="answer-option">{user.answer4}</td>
                  <td className="answer-option">{user.correct}</td>
                  {/* </div> */}
                  <td className="pool-actions">
                    <button onClick={() => handleShowEdit(user.qid)}>
                      Edit
                    </button>
                    <button onClick={() => deleteQues(user.qid)}>Delete</button>
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
                      <label>Question</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={question}
                        onChange={(e) => setQuestion(e.target.value)}
                      />{" "}
                      <br />
                      <label>Answer 1</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={answer1}
                        onChange={(e) => setAnswer1(e.target.value)}
                      />{" "}
                      <br />
                      <label>Answer 2</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={answer2}
                        onChange={(e) => setAnswer2(e.target.value)}
                      />{" "}
                      <br />
                      <label>Answer 3</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={answer3}
                        onChange={(e) => setAnswer3(e.target.value)}
                      />{" "}
                      <br />
                      <label>Answer 4</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={answer4}
                        onChange={(e) => setAnswer4(e.target.value)}
                      />{" "}
                      <br />
                      <label>Correct Answer</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={correct}
                        onChange={(e) => setCorrect(e.target.value)}
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
                  <Modal.Header>
                    <Modal.Title>Edit the question and answers</Modal.Title>
                  </Modal.Header>
                  <Modal.Body>
                    <form className="login">
                      <label>Question</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={question}
                        onChange={(e) => setQuestion(e.target.value)}
                      />{" "}
                      <br />
                      <label>Answer 1</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={answer1}
                        onChange={(e) => setAnswer1(e.target.value)}
                      />{" "}
                      <br />
                      <label>Answer 2</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={answer2}
                        onChange={(e) => setAnswer2(e.target.value)}
                      />{" "}
                      <br />
                      <label>Answer 3</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={answer3}
                        onChange={(e) => setAnswer3(e.target.value)}
                      />{" "}
                      <br />
                      <label>Answer 4</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={answer4}
                        onChange={(e) => setAnswer4(e.target.value)}
                      />{" "}
                      <br />
                      <label>Correct Answer</label> <br />
                      <input
                        className="admin--textfield"
                        type="text"
                        value={correct}
                        onChange={(e) => setCorrect(e.target.value)}
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
        <div className="cp-btn">
          <button className="secondary" onClick={handleShow}>
            Create Question
          </button>
        </div>
      </div>
    </div>
  );
}
