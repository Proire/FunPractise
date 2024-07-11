// pages/index.tsx
"use client";
import React, { useState, useEffect } from 'react';
import UserList from '../components/UserList';
import UserDetails from '../components/UserDetails';
import { User } from '../types/User';
import axios from 'axios';

const Home: React.FC = () => {
  const [selectedUser, setSelectedUser] = useState<User | null>(null);
  const [users, setUsers] = useState<User[]>([]);

  useEffect(() => {
    axios.get('http://localhost:3001/users')
      .then(response => {
        setUsers(response.data);
      })
      .catch(error => {
        console.error('Error fetching users:', error);
      });
  }, []);

  const handleUpdateUser = (updatedUser: User) => {
    const updatedUsers = users.map(u => u.id === updatedUser.id ? updatedUser : u);
    setUsers(updatedUsers);
    setSelectedUser(updatedUser); // Ensure selectedUser reflects the updated user
  };

  return (
    <div className="container">
      <div className="user-list-container">
        <UserList users={users} onSelectUser={setSelectedUser} />
      </div>
      <div className="user-details-container">
        <UserDetails user={selectedUser} onUpdateUser={handleUpdateUser} />
      </div>
    </div>
  );
};

export default Home;
