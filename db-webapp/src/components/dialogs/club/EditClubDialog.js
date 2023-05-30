import React, { useState, useEffect, useCallback } from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button,
    TextField,
    Box,
    Chip,
    Select,
    MenuItem,
    InputLabel,
} from '@mui/material';
import { MuiTelInput } from 'mui-tel-input';
import PlayerService from "../../../services/PlayerService";
import {toast} from "react-toastify";

const EditClubDialog = React.memo(({ isOpen, club, onSave, onCancel }) => {
    const [editedClub, setEditedClub] = useState(club);
    const [members, setMembers] = useState([]);
    const [availablePlayers, setAvailablePlayers] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [hasNextPage, setHasNextPage] = useState(false);

    useEffect(() => {
        setEditedClub(club);
        setMembers(club.members);
        fetchAvailablePlayers();
    }, [club]);

    const handleChange = useCallback(
        (event) => {
            const { name, value } = event.target;

            setEditedClub((prevClub) => ({
                ...prevClub,
                [name]: value,
            }));
        },
        []
    );

    const handleContactPersonChange = useCallback(
        (event) => {
            const { name, value } = event.target;

            setEditedClub((prevClub) => ({
                ...prevClub,
                contactPerson: {
                    ...prevClub.contactPerson,
                    [name]: value,
                },
            }));
        },
        []
    );

    const handlePhoneNumberChange = useCallback(
        (value) => {
            setEditedClub((prevClub) => ({
                ...prevClub,
                contactPerson: {
                    ...prevClub.contactPerson,
                    phoneNumber: value,
                },
            }));
        },
        []
    );

    const fetchAvailablePlayers = useCallback(() => {
        const page = currentPage;
        const size = 20;

        PlayerService.getAllPlayersByPage(page, size)
            .then((response) => {
                const { content, totalPages, number } = response;
                setAvailablePlayers((prevPlayers) => [...prevPlayers, ...content]);
                setHasNextPage(number + 1 < totalPages);
                setCurrentPage(number + 1);
            })
            .catch((error) => {
                toast.error("Problēma ar spēlētāju ielādi");
            });
    }, [currentPage]);

    const handleAddMember = useCallback(
        (playerId) => {
            const player = availablePlayers.find((p) => p.id === playerId);
            if (player) {
                setMembers((prevMembers) => [...prevMembers, player]);
            }
        },
        [availablePlayers]
    );

    const handleRemoveMember = useCallback(
        (memberId) => {
            setMembers((prevMembers) => prevMembers.filter((member) => member.id !== memberId));
        },
        []
    );

    const handleScroll = useCallback(() => {
        const scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        const scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
        const clientHeight = document.documentElement.clientHeight || window.innerHeight;

        if (scrollHeight - scrollTop === clientHeight) {
            fetchAvailablePlayers();
        }
    }, [fetchAvailablePlayers]);

    useEffect(() => {
        window.addEventListener('scroll', handleScroll);
        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, [handleScroll]);

    const handleSave = useCallback(
        (event) => {
            event.preventDefault();
            const updatedClub = { ...editedClub, members };
            onSave(updatedClub);
        },
        [editedClub, members, onSave]
    );

    const handleClose = useCallback(() => {
        setEditedClub(club);
        onCancel();
    }, [club, onCancel]);

    return (
        <Dialog open={isOpen} onClose={handleClose}>
            <DialogTitle>Edit Club</DialogTitle>
            <form onSubmit={handleSave}>
                <DialogContent>
                    <Box marginBottom={2} marginTop={2}>
                        <TextField
                            name="title"
                            label="Title"
                            value={editedClub.title || ''}
                            onChange={handleChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="contactPerson.firstName"
                            label="First Name"
                            value={editedClub.contactPerson.firstName || ''}
                            onChange={handleContactPersonChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="contactPerson.lastName"
                            label="Last Name"
                            value={editedClub.contactPerson.lastName || ''}
                            onChange={handleContactPersonChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <TextField
                            name="contactPerson.email"
                            type="email"
                            label="Email"
                            value={editedClub.contactPerson.email || ''}
                            onChange={handleContactPersonChange}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <MuiTelInput
                            langOfCountryName="lv"
                            preferredCountries={['LV']}
                            label="Phone Number"
                            value={editedClub.contactPerson.phoneNumber || ''}
                            onChange={handlePhoneNumberChange}
                            defaultCountry="lv"
                            regions={['europe']}
                            fullWidth
                            required
                        />
                    </Box>
                    <Box marginBottom={2}>
                        <InputLabel>Available Players</InputLabel>
                        <Select
                            multiple
                            value={[]}
                            onChange={(event) => handleAddMember(event.target.value)}
                            renderValue={(selected) => (
                                <div style={{ display: 'flex', flexWrap: 'wrap' }}>
                                    {selected.map((value) => (
                                        <Chip
                                            key={value}
                                            label={
                                                availablePlayers.find((player) => player.id === value)?.firstName || ''
                                            }
                                            onDelete={() => handleRemoveMember(value)}
                                        />
                                    ))}
                                </div>
                            )}
                        >
                            {availablePlayers.map((player) => (
                                <MenuItem key={player.id} value={player.id}>
                                    {`${player.firstName} ${player.lastName}`}
                                </MenuItem>
                            ))}
                        </Select>
                    </Box>
                    <Box marginBottom={2}>
                        <InputLabel>Current Members</InputLabel>
                        <div>
                            {members.map((member) => (
                                <Chip
                                    key={member.id}
                                    label={`${member.firstName} ${member.lastName}`}
                                    onDelete={() => handleRemoveMember(member.id)}
                                />
                            ))}
                        </div>
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

export default EditClubDialog;
