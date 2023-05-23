import React, { useState, useEffect, useCallback } from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button,
    TextField,
    Box,
} from '@mui/material';
import { MuiTelInput } from 'mui-tel-input';

const EditPersonDialog = React.memo(({ isOpen, person, onSave, onCancel }) => {
    const [editedPerson, setEditedPerson] = useState(person);

    useEffect(() => {
        setEditedPerson(person);
    }, [person]);

    const handleChange = useCallback(
        (event) => {
            const { name, value } = event.target;

            // Field validation
            let updatedValue = value;
            if (name === 'firstName' || name === 'lastName') {
                updatedValue = value.charAt(0).toUpperCase() + value.slice(1);
            }

            if (editedPerson[name] !== updatedValue) {
                setEditedPerson((prevPerson) => ({
                    ...prevPerson,
                    [name]: updatedValue,
                }));
            }
        },
        [editedPerson]
    );

    const handlePhoneNumberChange = useCallback(
        (value) => {
            if (editedPerson.phoneNumber !== value) {
                setEditedPerson((prevPerson) => ({
                    ...prevPerson,
                    phoneNumber: value,
                }));
            }
        },
        [editedPerson]
    );

    const handleSave = useCallback(
        (event) => {
            event.preventDefault();

            const updatedPerson = { ...editedPerson };
            onSave(updatedPerson);
        },
        [editedPerson, onSave]
    );

    const handleClose = useCallback(() => {
        setEditedPerson(person);
        onCancel();
    }, [person, onCancel]);

    return (
        <Dialog open={isOpen} onClose={handleClose}>
            <DialogTitle>Edit Person</DialogTitle>
            <form onSubmit={handleSave}>
                <DialogContent>
                    <Box marginBottom={2} marginTop={2}>
                        <TextField
                            name="firstName"
                            label="Vārds"
                            value={editedPerson.firstName || ''}
                            onChange={handleChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="lastName"
                            label="Uzvārds"
                            value={editedPerson.lastName || ''}
                            onChange={handleChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="email"
                            type="email"
                            label="E-pasts"
                            value={editedPerson.email || ''}
                            onChange={handleChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <MuiTelInput
                            langOfCountryName="lv"
                            preferredCountries={['LV']}
                            label="Telefona nr."
                            value={editedPerson.phoneNumber || ''}
                            onChange={handlePhoneNumberChange}
                            defaultCountry="lv"
                            regions={['europe']}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="birthDay"
                            label="Dzimšanas diena"
                            type="date"
                            value={editedPerson.birthDay || ''}
                            onChange={handleChange}
                            fullWidth
                            required
                            InputLabelProps={{
                                shrink: true,
                            }}
                            inputProps={{
                                max: new Date().toISOString().split('T')[0],
                            }}
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
});

export default EditPersonDialog;
