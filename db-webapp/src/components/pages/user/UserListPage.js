import React, { useState, useEffect } from 'react';
import UserAPI from "../../../services/UserService";
import UsersList from "../../../features/users/UsersList";
import AddUserDialog from "../../dialogs/AddUserDialog";
import { toast } from "react-toastify";
import userService from "../../../services/UserService";

const UserListPage = () => {
    const [isAddUserDialogOpen, setIsAddUserDialogOpen] = useState(false);
    const [users, setUsers] = useState([]);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        setIsLoading(true);
        userService.getAllUsers()
            .then((data) => {
                setUsers(data);
                setIsLoading(false);
            })
            .catch((error) => {
                console.error(error);
                toast.error('Neizdevās ielādēt lietotājus!');
                setIsLoading(false);
            });
    }, []);

    const handleAddUser = () => {
        setIsAddUserDialogOpen(true);
    };

    const handleCreateUser = (newUser) => {
        userService.createUser(newUser)
            .then((createdUser) => {
                setUsers((prevUsers) => [...prevUsers, createdUser]);
                toast.success('Lietotājs veiksmīgi pievienots!');
                setIsAddUserDialogOpen(false);
            })
            .catch((error) => {
                console.error(error);
                toast.error('Neizdevās pievienot lietotāju!');
            });
    };

    return (
        <div>
            <h1 className="primary">Lietotāji</h1>

            <button onClick={handleAddUser}>Pievienot lietotāju</button>

            {isLoading ? (
                <p>Loading...</p>
            ) : (
                <UsersList users={users} /> // Pass the users state as a prop
            )}

            {isAddUserDialogOpen && (
                <AddUserDialog
                    isOpen={isAddUserDialogOpen}
                    onCreateUser={handleCreateUser}
                    onCancel={() => setIsAddUserDialogOpen(false)}
                />
            )}
        </div>
    );
};


export default UserListPage;
