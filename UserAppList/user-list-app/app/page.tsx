// pages/index.tsx
"use client";
import React from 'react';
import UserList from '../components/UserList';
import UserDetails from '../components/UserDetails';
import { User } from '../types/User';

const Home: React.FC = () => {
  const [selectedUser, setSelectedUser] = React.useState<User | null>(null);

  return (
    <div className="container">
      <div className="user-list-container">
        <UserList onSelectUser={setSelectedUser} />
      </div>
      <div className="user-details-container">
        <UserDetails user={selectedUser} />
      </div>
    </div>
  );
};

export default Home;
