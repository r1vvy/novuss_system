import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import Login from "./features/auth/Login";
import DashLayout from "./components/dash/DashLayout";
import Welcome from "./features/auth/Welcome";
import {ClubsList} from "./features/clubs/ClubsList";
import {RefereesList} from "./features/referees/RefereesList";
import {UsersList} from "./features/users/UsersList";
import {PlayersList} from "./features/players/PlayersList";
import {CompetitionsList} from "./features/competitions/CompetitionsList";
import {BasesList} from "./features/bases/BasesList";
import Public from "./components/Public";

function App() {
  return (
    <Routes>
        <Route path="/*" element={<Layout />} >
            <Route index element={<Public />} />
            <Route path="login" element={<Login />} />

            <Route path="dash" element={<DashLayout />}>
                <Route index element={<Welcome />} />

                <Route path="bases">
                    <Route index element={<BasesList />} />
                </Route>
                <Route path="clubs">
                    <Route index element={<ClubsList />} />
                </Route>
                <Route path="competitions">
                    <Route index element={<CompetitionsList />} />
                </Route>
                <Route path="players">
                    <Route index element={<PlayersList />} />
                </Route>
                <Route path="referees">
                    <Route index element={<RefereesList />} />
                </Route>
                <Route path="users">
                    <Route index element={<UsersList />} />
                </Route>

            </Route>
        </Route>
    </Routes>
  );
}

export default App;
