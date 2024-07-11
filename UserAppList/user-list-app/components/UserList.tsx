import React from 'react';
import axios from 'axios';
import { User } from '../types/User';

interface UserListProps {
  onSelectUser: (user: User) => void;
}

const UserList: React.FC<UserListProps> = ({ onSelectUser }) => {
  const [users, setUsers] = React.useState<User[]>([]);

  React.useEffect(() => {
    axios.get('http://localhost:3001/users') // Assuming JSON Server is running locally on port 3000
      .then(response => {
        setUsers(response.data);
      })
      .catch(error => {
        console.error('Error fetching users:', error);
      });
  }, []);

  return (
    <div className="user-list">
      <h2>User List</h2>
      <ul>
        {users.map(user => (
          <li key={user.id} onClick={() => onSelectUser(user)}>
            {user.name}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default UserList;
