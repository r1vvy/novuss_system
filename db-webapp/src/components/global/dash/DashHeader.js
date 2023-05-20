import { Link } from "react-router-dom";
import { AppBar, Toolbar, Typography, styled } from '@mui/material';

const DashHeaderContainer = styled('div')({
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
});

const DashHeaderTitle = styled(Link)(({ theme }) => ({
    textDecoration: 'none',
    color: theme.palette.text.secondary,
}));

const DashHeader = () => {
    return (
        <AppBar position="static" color="primary">
            <Toolbar>
                <DashHeaderContainer>
                    <DashHeaderTitle to="/dash">
                        <Typography variant="h6">LNF</Typography>
                    </DashHeaderTitle>
                    {/* Add your navigation items here using Button or any other desired component */}
                </DashHeaderContainer>
            </Toolbar>
        </AppBar>
    );
};

export default DashHeader;
