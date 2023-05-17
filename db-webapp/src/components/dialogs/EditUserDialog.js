import React, { useState } from 'react';

const EditUserDialog = ({ isOpen, user, onSave, onCancel }) => {
    const [updatedUser, setUpdatedUser] = useState(user);

    if (!isOpen) {
        return null;
    }

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUpdatedUser((prevUser) => ({
            ...prevUser,
            [name]: value,
        }));
    };

    const handleSave = () => {
        onSave(updatedUser);
    };

    const handleCancel = () => {
        onCancel();
        setUpdatedUser(user); // Reset the form to the initial user data
    };

    return (
        <div>
            <h2>Edit User</h2>
            <label>
                Username:
                <input
                    type="text"
                    name="username"
                    value={updatedUser.username}
                    onChange={handleInputChange}
                />
            </label>
            <label>
                Email:
                <input
                    type="text"
                    name="email"
                    value={updatedUser.email}
                    onChange={handleInputChange}
                />
            </label>
            <button onClick={handleSave}>Save</button>
            <button onClick={handleCancel}>Cancel</button>
        </div>
    );
};

export default EditUserDialog;
