import React, { useState, useCallback } from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button,
    TextField,
    Box,
} from '@mui/material';

const AddClubDialog = ({ isOpen, onCreateClub, onCancel }) => {
    const [clubData, setClubData] = useState({
        name: '',
        contact: {
            name: '',
            email: '',
            phoneNumber: '',
        },
        location: {
            name: '',
            city: '',
            address: '',
        },
    });

    const handleChange = useCallback(
        (event) => {
            const { name, value } = event.target;
            setClubData((prevData) => ({
                ...prevData,
                [name]: value,
            }));
        },
        []
    );

    const handleContactChange = useCallback(
        (event) => {
            const { name, value } = event.target;
            setClubData((prevData) => ({
                ...prevData,
                contact: {
                    ...prevData.contact,
                    [name]: value,
                },
            }));
        },
        []
    );

    const handleLocationChange = useCallback(
        (event) => {
            const { name, value } = event.target;
            setClubData((prevData) => ({
                ...prevData,
                location: {
                    ...prevData.location,
                    [name]: value,
                },
            }));
        },
        []
    );

    const handleSave = useCallback(
        (event) => {
            event.preventDefault();
            onCreateClub(clubData);
        },
        [clubData, onCreateClub]
    );

    const handleClose = useCallback(() => {
        setClubData({
            name: '',
            contact: {
                name: '',
                email: '',
                phoneNumber: '',
            },
            location: {
                name: '',
                city: '',
                address: '',
            },
        });
        onCancel();
    }, [onCancel]);

    return (
        <Dialog open={isOpen} onClose={handleClose}>
            <DialogTitle>Add Club</DialogTitle>
            <form onSubmit={handleSave}>
                <DialogContent>
                    <Box marginBottom={2}>
                        <TextField
                            name="name"
                            label="Club Name"
                            value={clubData.name}
                            onChange={handleChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="contact.name"
                            label="Contact Name"
                            value={clubData.contact.name}
                            onChange={handleContactChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="contact.email"
                            type="email"
                            label="Contact Email"
                            value={clubData.contact.email}
                            onChange={handleContactChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="contact.phoneNumber"
                            label="Contact Phone Number"
                            value={clubData.contact.phoneNumber}
                            onChange={handleContactChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="location.name"
                            label="Location Name"
                            value={clubData.location.name}
                            onChange={handleLocationChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="location.city"
                            label="Location City"
                            value={clubData.location.city}
                            onChange={handleLocationChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="location.address"
                            label="Location Address"
                            value={clubData.location.address}
                            onChange={handleLocationChange}
                            fullWidth
                            required
                        />
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button type="submit" variant="contained" color="primary">
                        Save
                    </Button>
                </DialogActions>
            </form>
        </Dialog>
    );
};

export default AddClubDialog;
