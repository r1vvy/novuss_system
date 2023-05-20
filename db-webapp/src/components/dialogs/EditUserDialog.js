import React, { useState, useEffect } from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, Button, TextField, Box } from '@mui/material';

const EditUserDialog = ({ isOpen, user, onSave, onCancel }) => {
    const [editedUser, setEditedUser] = useState(user);

    useEffect(() => {
        setEditedUser(user);
    }, [user]);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setEditedUser((prevUser) => ({
            ...prevUser,
            [name]: value,
        }));
    };

    const handleSave = (event) => {
        event.preventDefault();

        const updatedUser = { ...editedUser };
        onSave(updatedUser);
    };

    const handleClose = () => {
        setEditedUser(user);
        onCancel();
    };

    return (
        <Dialog open={isOpen} onClose={handleClose}>
            <DialogTitle>Edit User</DialogTitle>
            <form onSubmit={handleSave}>
                <DialogContent>
                        <Box marginBottom={2} marginTop={2}>
                            <TextField
                                name="username"
                                label="Lietotājvārds"
                                value={editedUser.username}
                                onChange={handleChange}
                                fullWidth
                                required
                            />
                        </Box>
                        <Box marginBottom={2}>
                            <TextField
                                name="email"
                                label="E-pasts"
                                value={editedUser.email}
                                onChange={handleChange}
                                fullWidth
                                required
                            />
                        </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Atcelt</Button>
                    <Button type="submit" variant="contained" color="primary">
                        Saglabāt
                    </Button>
                </DialogActions>
            </form>
        </Dialog>
    );
};

export default EditUserDialog;
