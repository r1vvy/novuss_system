import React, { useEffect, useState } from 'react';
import UserService from "../../services/UserService";
import DataTable from "../../components/DataTable";
import ConfirmationDialog from "../../components/dialogs/ConfirmationDialog";
import EditUserDialog from "../../components/dialogs/EditUserDialog";
import {toast} from "react-toastify";
import {Button, IconButton} from "@mui/material";
import {Delete, Edit} from "@mui/icons-material";

const userService = UserService;

const UsersList = () => {
    const [users, setUsers] = useState([]);
    const [selectedUser, setSelectedUser] = useState(null);
    const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);
    const [isEditDialogOpen, setIsEditDialogOpen] = useState(false);

    useEffect(() => {
        // Fetch users data from the API
        userService.getAllUsers()
            .then((data) => {
                setUsers(data)
            })
            .catch((error) => {
                console.error(error);
                toast.error('Neizdevās ielādēt lietotājus!');
            });
    }, []);

    const columns = [
        { field: 'id', headerName: 'ID', hide: true },
        { field: 'username', headerName: 'Lietotājvārds'},
        { field: 'email', headerName: 'E-pasts'},
        { field: 'roles', headerName: 'Lomas'},
        {
            field: 'actions',
            headerName: 'Darbības',
            renderCell: (rowData) => (
                <>
                    <IconButton color="primary" onClick={() => handleEditUser(rowData)}>
                        <Edit />
                    </IconButton>

                    <IconButton color="tertiary-secondary" onClick={() => handleDeleteUser(rowData)}>
                        <Delete />
                    </IconButton>
                </>
            ),
        },
    ];

    const sortableColumns = ['username', 'email']; // Specify the sortable columns

    const handleDeleteUser = (userData) => {
        setSelectedUser(userData);
        setIsDeleteDialogOpen(true);
        setIsEditDialogOpen(false); // Close the edit dialog if it's open
    };

    const handleConfirmDelete = () => {
        if (selectedUser) {
            console.log(`Selected user id: ${selectedUser.id}`)
            userService.deleteUser(selectedUser.id)
                .then(() => {
                    // Remove the deleted user from the state
                    setUsers((prevUsers) => prevUsers.filter((user) => user.id !== selectedUser.id));
                    toast.success('Lietotājs veiksmīgi dzēsts!');
                })
                .catch((error) => {
                    console.error(error);
                    toast.error('Neizdevās dzēst lietotāju!');
                });
        }

        setIsDeleteDialogOpen(false);
    };

    const handleEditUser = (userData) => {
        setSelectedUser(userData);
        setIsEditDialogOpen(true);
        setIsDeleteDialogOpen(false); // Close the delete dialog if it's open
    };

    const handleUpdateUser = (updatedUser) => {
        userService.updateUser(updatedUser)
            .then(() => {
                // Update the user in the state
                setUsers((prevUsers) => {
                    prevUsers.map((user) => (user.id === updatedUser.id ? updatedUser : user))
                    toast.success('Lietotāja dati veiksmīgi atjaunoti!')
                });
            })
            .catch((error) => {
                console.error(error);
                toast.error('Neizdevās atjaunot lietotāja datus!');
            });

        setIsEditDialogOpen(false);
    };

    return (
        <div>
            <h1 className="primary">Lietotāji</h1>
            <DataTable data={users} columns={columns} sortableColumns={sortableColumns} />
            <ConfirmationDialog
                isOpen={isDeleteDialogOpen}
                message="Vai esat pārliecināts, ka vēlaties dzēst šo lietotāju?"
                onConfirm={handleConfirmDelete}
                onCancel={() => setIsDeleteDialogOpen(false)}
            />
            {selectedUser && (
                <EditUserDialog
                    isOpen={isEditDialogOpen}
                    user={selectedUser}
                    onSave={handleUpdateUser}
                    onCancel={() => setIsEditDialogOpen(false)}
                />
            )}
        </div>
    );
};

export default UsersList;
