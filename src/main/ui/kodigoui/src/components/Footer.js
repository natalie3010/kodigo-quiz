import React, { useState } from "react";
import "./Home.css";
import fb from "./img/fb.svg";
import ig from "./img/ig.svg";
import tweet from "./img/tweet.svg";

export default function Footer() {
  return (
    <div class="footer">
      <div className="f-content">
        <p>&copy; {new Date().getFullYear()} Copyright: KODIGO</p>
        <ul class="menu-simple" style={{ listStyleType: "none" }}>
          <li>
            <a href="https://en-gb.facebook.com/">
              <img src={fb} width="25" height="25" />
            </a>
          </li>
          <li>
            <a href="https://www.instagram.com/?hl=en">
              <img src={ig} width="25" height="25" />
            </a>
          </li>
          <li>
            <a href="https://twitter.com/?lang=en">
              <img src={tweet} width="25" height="25" />{" "}
            </a>
          </li>
        </ul>
      </div>
    </div>
  );
}
