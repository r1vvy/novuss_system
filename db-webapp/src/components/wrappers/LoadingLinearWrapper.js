import React from 'react';
import { LinearProgress } from '@mui/material';

const LoadingLinearWrapper = ({ isLoading, children }) => {

    if (isLoading) {
        return <LinearProgress color="primary" />;
    }

    return <>{children}</>;
};

export default LoadingLinearWrapper;
