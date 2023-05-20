import React, { useState, useEffect } from 'react';
import UserAPI from "../../../services/UserService";
import UsersList from "../../../features/users/UsersList";
import AddUserDialog from "../../dialogs/AddUserDialog";
import {toast} from "react-toastify";

const UserListPage = () => {
    const [isAddUserDialogOpen, setIsAddUserDialogOpen] = useState(false);
    const [users, setUsers] = useState([]);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            setIsLoading(true);
            const userList = await UserAPI.getAllUsers();
            setUsers(userList);
            setIsLoading(false);
        } catch (error) {
            setIsLoading(false);
            toast.error('Neizdevās iegūt lietotājus');
        }
    };

    const handleAddUser = () => {
        setIsAddUserDialogOpen(true);
    };

    const handleCreateUser = async (newUser) => {
        try {
            await UserAPI.createUser(newUser);
            setIsAddUserDialogOpen(false);
            await fetchUsers(); // Refresh the user list
            toast.success('Lietotājs veiksmīgi izveidots');
        } catch (error) {
            console.error(error);
            toast.error('Neizdevās izveidot lietotāju');
        }
    };

    return (
        <div>
            <h1 className="primary">Lietotāji</h1>

            <button onClick={handleAddUser}>Pievienot lietotāju</button>

            {isLoading ? (
                <p>Loading...</p>
            ) : (
                <UsersList users={users} />
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
