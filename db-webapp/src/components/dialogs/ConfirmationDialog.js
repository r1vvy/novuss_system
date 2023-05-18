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
            <button onClick={onConfirm}>Dzēst</button>
            <button onClick={handleCancel}>Atcelt</button>
        </div>
    );
};

export default ConfirmationDialog;
