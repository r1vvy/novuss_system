import React from 'react';
import { Link } from 'react-router-dom';
import { Box, Button, Container, Typography } from '@mui/material';
import LogoImage from '../../img/lnf-head-logo-01.png';
import BackgroundImage from '../../img/lnf-background-01.jpg';

const Public = () => {
    const content = (
        <Container
            maxWidth="xl"
            sx={{
                display: 'flex',
                alignItems: 'center',
                height: '100vh',
                flexDirection: { xs: 'column', md: 'row' },
            }}
        >
            <Box
                sx={{
                    width: { xs: '100%', md: '50%' },
                    p: { xs: 4, md: 8 },
                    backgroundColor: 'primary.main',
                    color: 'white',
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    justifyContent: 'center',
                }}
            >
                <img
                    src={LogoImage}
                    alt="Logo"
                    style={{ width: '50%', marginBottom: '2rem', filter: 'brightness(0) invert(1)' }}
                />
                <Typography variant="h4" align="center" gutterBottom>
                    Datu Pārvaldības Sistēma
                </Typography>
                <Typography variant="body1" align="center" sx={{ mb: 4 }}>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eleifend magna eu massa tincidunt, vitae pretium massa rutrum.
                </Typography>
                <Button
                    component={Link}
                    to="/login"
                    variant="outlined"
                    sx={{ color: 'white', borderColor: 'white' }}
                >
                    AUTORIZĒTA PERSONĀLA PIEKĻUVE
                </Button>
            </Box>
            <Box
                sx={{
                    width: { xs: '100%', md: '50%' },
                    display: { xs: 'block', md: 'block' },
                }}
            >
                <img
                    src={BackgroundImage}
                    alt="Background"
                    style={{ width: '100%', height: '100%', objectFit: 'cover' }}
                />
            </Box>
        </Container>
    );

    return content;
};

export default Public;
