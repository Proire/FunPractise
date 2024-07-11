import React, { useState } from 'react';
import axios from 'axios';
import { User } from '../types/User';

interface UserDetailsProps {
  user: User | null;
  onUpdateUser: (updatedUser: User) => void;
}

const UserDetails: React.FC<UserDetailsProps> = ({ user, onUpdateUser }) => {
  const [selectedTab, setSelectedTab] = useState<'profile' | 'contact'>('profile');
  const [isEditing, setIsEditing] = useState(false);
  const [editableUser, setEditableUser] = useState<User | null>(user);

  React.useEffect(() => {
    setEditableUser(user);
  }, [user]);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!editableUser) return;
    const { name, value } = e.target;
    setEditableUser({ ...editableUser, [name]: value });
  };

  const handleSave = () => {
    if (editableUser) {
      axios.put(`http://localhost:3001/users/${editableUser.id}`, editableUser)
        .then(response => {
          setIsEditing(false);
          onUpdateUser(editableUser); // Update the user in the parent component
        })
        .catch(error => {
          console.error("There was an error updating the user!", error);
        });
    }
  };

  if (!user) {
    return <div className="user-details">Please select a user.</div>;
  }

  return (
    <div className="user-details">
      <h2>User Details</h2>
      <div className="tabs">
        <button className={selectedTab === 'profile' ? 'active' : ''} onClick={() => setSelectedTab('profile')}>Profile</button>
        <button className={selectedTab === 'contact' ? 'active' : ''} onClick={() => setSelectedTab('contact')}>Contacts</button>
      </div>
      {selectedTab === 'profile' ? (
        <div className="tab-content">
          <p><strong>Name:</strong>
            {isEditing ? (
              <input type="text" name="name" value={editableUser?.name} onChange={handleInputChange} />
            ) : (
              user.name
            )}
          </p>
          <p><strong>DOB:</strong>
            {isEditing ? (
              <input type="date" name="dob" value={editableUser?.dob} onChange={handleInputChange} />
            ) : (
              user.dob
            )}
          </p>
          <p><strong>Address:</strong>
            {isEditing ? (
              <input type="text" name="address" value={editableUser?.address} onChange={handleInputChange} />
            ) : (
              user.address
            )}
          </p>
        </div>
      ) : (
        <div className="tab-content">
          <p><strong>Email:</strong> {user.email}</p>
          <p><strong>Phone:</strong> {user.phone}</p>
        </div>
      )}
      <div className="actions">
        {isEditing ? (
          <button onClick={handleSave}>Save</button>
        ) : (
          <button onClick={() => setIsEditing(true)}>Edit</button>
        )}
      </div>
    </div>
  );
};

export default UserDetails;
