import React, { useEffect, useState } from 'react';
import UserService from "../../services/UserService";
import DataTable from "../../components/DataTable";
import ConfirmationDialog from "../../components/dialogs/ConfirmationDialog";
import EditUserDialog from "../../components/dialogs/user/EditUserDialog";
import { toast } from "react-toastify";
import { IconButton } from "@mui/material";
import { Delete, Edit } from "@mui/icons-material";

const UsersList = ({ users }) => {
    const [userList, setUserList] = useState(users);
    const [selectedUser, setSelectedUser] = useState(null);
    const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);
    const [isEditDialogOpen, setIsEditDialogOpen] = useState(false);

    useEffect(() => {
        setUserList(users);
    }, [users]);

    const columns = [
        { field: 'username', headerName: 'Lietotājvārds' },
        { field: 'email', headerName: 'E-pasts' },
        { field: 'roles', headerName: 'Lomas' },
        {
            field: 'actions',
            headerName: 'Darbības',
            sortable: false,
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

    const sortableColumns = ['username', 'email'];

    const handleDeleteUser = (userData) => {
        setSelectedUser(userData);
        setIsDeleteDialogOpen(true);
        setIsEditDialogOpen(false);
    };

    const handleEditUser = (userData) => {
        setSelectedUser(userData.row);
        setIsEditDialogOpen(true);
        setIsDeleteDialogOpen(false);
    };

    const handleConfirmDelete = () => {
        if (selectedUser) {
            UserService.deleteUser(selectedUser.id)
                .then(() => {
                    // Remove the deleted user from the state
                    setUserList((prevUsers) => prevUsers.filter((user) => user.id !== selectedUser.id));
                    toast.success('Lietotājs veiksmīgi dzēsts!');
                })
                .catch((error) => {
                    toast.error('Neizdevās dzēst lietotāju!');
                });
        }

        setIsDeleteDialogOpen(false);
    };


    const handleConfirmUpdateUser = (updatedUser) => {
        if (updatedUser) {
            UserService.updateUser(updatedUser)
                .then(() => {
                    setUserList((prevUsers) =>
                        prevUsers.map((user) => (user.id === updatedUser.id ? updatedUser : user))
                    );
                    toast.success('Lietotāja dati veiksmīgi atjaunoti!');
                    setIsEditDialogOpen(false);
                })
                .catch((error) => {
                    toast.error('Neizdevās atjaunot lietotāja datus!');
                });
        }
    };

    return (
        <div>
            <DataTable data={userList} columns={columns} sortableColumns={sortableColumns} />
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
                    onSave={handleConfirmUpdateUser}
                    onCancel={() => setIsEditDialogOpen(false)}
                />
            )}
        </div>
    );
};

export default UsersList;
