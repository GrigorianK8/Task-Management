import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import { Button, Grid, TextField } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import { useLocation } from 'react-router-dom';
import { submitTask } from '../../../redux/SubmissionSlice';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  outline: 'none',
  boxShadow: 24,
  p: 4,
};

export default function SubmitForm({item, handleClose, open}) {
  const dispatch = useDispatch();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
	const taskId = queryParams.get('taskId');
  const {task} = useSelector(store=>store);
  const [formData, setFormData] = useState({
    githubLink: "",
    description: "",
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(submitTask({taskId,githubLink:formData.githubLink}));
    handleClose();
  };

  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <form onSubmit={handleSubmit}>
            <Grid container spacing={2} alignItems='center'>
              <Grid item xs={12}>
                <TextField
                label='Github Link'
                fullWidth
                name='githubLink'
                value={formData.githubLink}
                onChange={handleChange}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                label='Description'
                fullWidth
                multiline
                rows={4}
                name='description'
                value={formData.description}
                onChange={handleChange}
                />
              </Grid>
              <Grid item xs={12}>
                <Button className='customeButton'
                type='submit'
                fullWidth
                sx={{padding: '.9rem'}}
                >
                  submit
                </Button>
              </Grid>
            </Grid>
          </form>
        </Box>
      </Modal>
    </div>
  );
}