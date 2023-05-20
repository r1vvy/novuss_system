import React from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, Button } from '@mui/material';

const ConfirmationDialog = ({ isOpen, message, onConfirm, onCancel }) => {
    const handleCancel = () => {
        onCancel();
    };

    const handleConfirm = () => {
        onConfirm();
    };

    return (
        <Dialog open={isOpen} onClose={handleCancel}>
            <DialogTitle>Apstiprināt</DialogTitle>
            <DialogContent>{message}</DialogContent>
            <DialogActions>
                <Button onClick={handleCancel}>Atcelt</Button>
                <Button onClick={handleConfirm} variant="contained" color="secondary">
                    Dzēst
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default ConfirmationDialog;
