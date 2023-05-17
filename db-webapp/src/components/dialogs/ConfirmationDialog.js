import React from 'react';

const ConfirmationDialog = ({ isOpen, message, onConfirm, onCancel }) => {
    if (!isOpen) {
        return null;
    }

    const handleCancel = () => {
        onCancel();
    };

    return (
        <div>
            <p>{message}</p>
            <button onClick={onConfirm}>Confirm</button>
            <button onClick={handleCancel}>Cancel</button>
        </div>
    );
};

export default ConfirmationDialog;
