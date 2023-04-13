import React from 'react';
import { HashRouter as Router, Routes, Route, HashRouter } from 'react-router-dom';
import './style.css';
import Home from './components/Home';
import AdminSignup from './components/AdminSignup';
import AdminLogin from './components/AdminLogin';
import Navbarpool from './components/Navbarpool';
import PoolList from './components/PoolList';
import GroupList from './components/GroupList';
import PlayerPage from './components/PlayerPage';
import QuestionList from './components/QuestionList';
import AdminWelcome from './components/AdminWelcome';
import PlayerLeaderboard from './components/PlayerLeaderboard';
import Leaderboard from './components/Leaderboard';
import LeaderboardList from './components/LeaderboardList';






function App() {
  return (

    <>
    
      <HashRouter>
        
        <Routes>
           <Route path='/' element={<Home />} />
           <Route path='/adminlogin' element={<AdminLogin />} />
           <Route path='/adminwelcome/:pin' element={<AdminWelcome />} />
           <Route path='/adminsignup' element={<AdminSignup />} />
           <Route path='/navbarpool' element={<Navbarpool />} />
           <Route path='/pool/:adid' element={<PoolList />} />
           <Route path='/group/:qpid' element={<GroupList />} />
           <Route path='/play/:pin/:playername' element={<PlayerPage />} />
           <Route path='/questions/:qgid' element={<QuestionList />} />
           <Route path='/play/leader/:qgid' element={<PlayerLeaderboard />} />
           <Route path='/group/leaderboard/:qgid' element={<Leaderboard />} />
           <Route path='/group/leaderboard/list/:qgid/:playername' element={<LeaderboardList />} />
        </Routes>
          
      </HashRouter>
              
          
    </>
  );
}

export default App;