import React, { useState } from 'react';
import { ROLES } from "../../../app/roles";
import {
    Box,
    Button,
    Checkbox, Dialog,
    FormControl,
    FormControlLabel,
    FormGroup,
    TextField,
} from '@mui/material';
import theme from "../../../theme/createTheme";

const AddUserDialog = ({ isOpen, onCreateUser, onCancel }) => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [selectedRoles, setSelectedRoles] = useState([]);
    const [errors, setErrors] = useState({});

    const handleCreateClick = (event) => {
        event.preventDefault(); // Prevent form submission
        const newUser = {
            username,
            email,
            password,
            roles: selectedRoles
        };
        onCreateUser(newUser);
        clearFields();
    };

    const clearFields = () => {
        setUsername('');
        setEmail('');
        setPassword('');
        setSelectedRoles([]);
        setErrors({});
    };

    const handleRoleChange = (role) => {
        if (selectedRoles.includes(role)) {
            setSelectedRoles(selectedRoles.filter((r) => r !== role));
        } else {
            setSelectedRoles([...selectedRoles, role]);
        }
    };

    return (
        <Dialog open={isOpen} onClose={onCancel}
                sx={{
                    '& .MuiDialog-paper': {
                        width: '400px',
                        padding: '2rem',
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    },
                }}
        >
            <h2 style={{ textAlign: 'center' }}>Pievienot lietotāju</h2>
            <form
                onSubmit={handleCreateClick}
                style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}
            >
                <FormControl style={{ marginBottom: '1rem', width: '300px' }}>
                    <TextField
                        type="text"
                        id="username"
                        label="Lietotājvārds"
                        variant="outlined"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                        error={!!errors.username}
                        helperText={errors.username}
                    />
                </FormControl>

                <FormControl style={{ marginBottom: '1rem', width: '300px' }}>
                    <TextField
                        type="email"
                        id="email"
                        label="E-pasts"
                        variant="outlined"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        error={!!errors.email}
                        helperText={errors.email}
                    />
                </FormControl>

                <FormControl style={{ marginBottom: '1rem', width: '300px' }}>
                    <TextField
                        type="password"
                        id="password"
                        label="Parole"
                        variant="outlined"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        error={!!errors.password}
                        helperText={errors.password}
                    />
                </FormControl>

                <FormControl component="fieldset" style={{ width: '300px' }}>
                    <FormGroup>
                        <legend>Lomas</legend>
                        {Object.keys(ROLES).map((key) => {
                            const role = ROLES[key];
                            return (
                                <FormControlLabel
                                    key={role}
                                    control={
                                        <Checkbox
                                            checked={selectedRoles.includes(role)}
                                            onChange={() => handleRoleChange(role)}
                                            color="primary"
                                        />
                                    }
                                    label={role}
                                />
                            );
                        })}
                    </FormGroup>
                </FormControl>

                <Box
                    sx={{
                        display: 'flex',
                        justifyContent: 'space-between',
                        width: '100%',
                        marginTop: '2rem',
                    }}
                >
                    <Button
                        type="submit"
                        variant="contained"
                        sx={{
                            width: '150px',
                            marginRight: '1rem',
                            backgroundColor: theme.palette.secondary.main,
                            color: '#fff',
                        }}
                    >
                        Izveidot
                    </Button>
                    <Button
                        type="button"
                        variant="contained"
                        onClick={onCancel}
                        sx={{
                            width: '150px',
                            marginLeft: '1rem',
                            backgroundColor: theme.palette.secondary.secondary,
                            color: '#fff',
                        }}
                    >
                        Atcelt
                    </Button>
                </Box>
            </form>
        </Dialog>
    );
};

export default AddUserDialog;
