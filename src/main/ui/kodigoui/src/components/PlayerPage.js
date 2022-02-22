import React, { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import './Questions.css';
import Navbar from "./Navbar";




export default function PlayerPage() {

  const { pin } = useParams();
  const { playername } = useParams();

  useEffect(() => {
    fetch(`/groups/${pin}`)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setNewQuestion(data);
        
      });
  }, []);



  const [newQuestion, setNewQuestion] = useState(0);
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const [showScore, setShowScore] = useState(false);
  const [score, setScore] = useState(0);
  const [showAnswer, setShowAnswers] = useState(false);
  const [disable, setDisable] = useState(false);
  const [qid, setQid] = useState(0);
  const [answer, setAnswer] = useState("");


  

    async function PlayerSave() {
    let item = { playername, answer, score }
    console.log(qid)
    console.log(playername)
    console.log(answer)
    console.log(score)


    let result = await fetch(`/player/register/${qid}/${pin}`, {
        method: 'POST',
        body: JSON.stringify(item),
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Access-Control-Allow-Origin': '*'
        }

    })
    result = await result.json()
    console.log(result)
    setQid(newQuestion[currentQuestion].qid);
}


  const handleAnswerOptionClick = (answer) => {
    console.log(qid);
    console.log(answer);
    setDisable(true);
    setAnswer(answer);

    if (answer === newQuestion[currentQuestion].correct) {
      console.log(newQuestion[currentQuestion].correct)
      setScore(score + 1);
    }

    console.log(score);

    setShowAnswers(true);
    


  };

  function Timer({ currentQuestion }) {
    const [timer, setTimer] = useState(5);

    useEffect(() => {

      const interval = setInterval(() => {
        setTimer((prev) => prev - 1);
      }, 1000);

      if(timer === 5) {
        setQid(newQuestion[currentQuestion].qid);
      }

      if (timer === 0) {
        setShowAnswers(false);
        setDisable(false);
        const nextQuestion = currentQuestion + 1;
        if (nextQuestion < newQuestion.length) {
          setCurrentQuestion(nextQuestion);
          
          PlayerSave();
        } else {
          PlayerSave();
          setShowScore(true);
        }
      }

      return () => clearInterval(interval);

    });
    return timer;

  }

  



  return newQuestion.length > 0 ? (
    <div>

      <Navbar />



      <div className='quiz-containerp'>
        {showScore ? (
          <div className='score-section'>
            You scored {score} out of {newQuestion.length}
            <div className='container--buttons'>
              <Link to={'/play/leader/' + pin}> <button>View Leaderboard</button></Link>
            </div>
          </div>
        ) : (
          <>
            <div class="question-section">
              <div id="countdown"><div id="countdown-number"><Timer currentQuestion={currentQuestion} /></div><svg> <circle r="18" cx="20" cy="20"></circle> </svg></div>
              <div class="question-count">
                <span>Question {currentQuestion + 1}</span>/{newQuestion.length}
                <h1 id="question-text">{newQuestion[currentQuestion].question}</h1>
              </div>


              <div className="answer-section">
                <button disabled={disable} onClick={(answer) => handleAnswerOptionClick(answer = newQuestion[currentQuestion].answer1)} className={showAnswer ? newQuestion[currentQuestion].answer1 === newQuestion[currentQuestion].correct ? "correct" : "incorrect" : "answer-button"}>{newQuestion[currentQuestion].answer1}</button>
                <button disabled={disable} onClick={(answer) => handleAnswerOptionClick(answer = newQuestion[currentQuestion].answer2)} className={showAnswer ? newQuestion[currentQuestion].answer2 === newQuestion[currentQuestion].correct ? "correct" : "incorrect" : "answer-button"}>{newQuestion[currentQuestion].answer2}</button>
                <button disabled={disable} onClick={(answer) => handleAnswerOptionClick(answer = newQuestion[currentQuestion].answer3)} className={showAnswer ? newQuestion[currentQuestion].answer3 === newQuestion[currentQuestion].correct ? "correct" : "incorrect" : "answer-button"}>{newQuestion[currentQuestion].answer3}</button>
                <button disabled={disable} onClick={(answer) => handleAnswerOptionClick(answer = newQuestion[currentQuestion].answer4)} className={showAnswer ? newQuestion[currentQuestion].answer4 === newQuestion[currentQuestion].correct ? "correct" : "incorrect" : "answer-button"}>{newQuestion[currentQuestion].answer4}</button>
              </div>
            </div>
          </>
        )}
      </div>

    </div>
  ) : (
    <p> Loading </p>
  );
}