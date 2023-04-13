import React from "react";
import { Link } from "react-router-dom";
import "./Navbarpool.css";
import logo from "./img/logo.svg";
import user from "./img/user.svg";

export default function Navbar() {
  function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
  }

  window.onclick = function (event) {
    if (!event.target.matches(".dropbtn")) {
      var dropdowns = document.getElementsByClassName("dropdown-content");
      var i;
      for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains("show")) {
          openDropdown.classList.remove("show");
        }
      }
    }
  };
  return (
    <div className="navpool">
      <div className="n-logo">
        <Link to="/">
          <img src={logo} width="150" height="50" />
        </Link>
      </div>
      <div className="Sign-out">
        <Link to="/">
          <button className="sign-out-btn">Sign Out</button>
        </Link>
      </div>
    </div>
  );
}
