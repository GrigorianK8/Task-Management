import React, { useEffect } from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import SubmissionCard from './taskCard/SubmissionCard';
import { useDispatch, useSelector } from 'react-redux';
import { fetchSubmissionsByTaskId } from '../../redux/SubmissionSlice';
import { useLocation } from 'react-router-dom';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 500,
  bgcolor: 'background.paper',
  outline: 'none',
  boxShadow: 24,
  p: 4,
};

const submissions = [1,1,1]

export default function SubmissionList({handleClose, open}) {
  const dispatch = useDispatch();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
	const taskId = queryParams.get('taskId');
  const {submission} = useSelector(store=>store);

  useEffect(()=> {
    if(taskId){
      dispatch(fetchSubmissionsByTaskId(taskId))
    }
  }, [taskId]);
  
  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <div>
            {submission.submissions.length > 0 ?<div className='space-y-2'>
              {submission.submissions.map((item) => <SubmissionCard item={item}/>)}
            </div>:<div>
            <div className='text-center'>No Submission Found</div>
          </div> }
          </div>
        </Box>
      </Modal>
    </div>
  );
}