import React, { useState } from 'react';
import './Auth.css';
import Signin from './Signin';
import Signup from './Signup';

const Auth = () => {
  const [isRegister, setIsRegister] = useState(false);
  const togglePanel = () => {
    setIsRegister(!isRegister);
  };

  return (
    <div className='flex justify-center h-screen items-center overflow-hidden'>
      <div className='box lg:max-w-4xl'>
        <div className={`cover ${isRegister?'rotate-active':''}`}>
          <div className='front'>
            <img 
            src="https://cdn.pixabay.com/photo/2020/12/03/23/35/work-5801876_1280.jpg" 
            alt="" />
            <div className='text'>
              <span className='text-1'>Success is built upon well-organized tasks</span>
              <span className='text-2 text-xs'>Let's get connected</span>
            </div>
          </div>
          <div className='back'>
            <img 
            src="https://cdn.pixabay.com/photo/2017/08/01/19/48/people-2567566_1280.jpg" 
            alt="" />
          </div>
        </div>
        <div className='forms h-full'>
          <div className='form-content h-full'>
            <div className='login-form'>
              <Signin togglePanel={togglePanel} />
            </div>
            <div className='signup-form'>
              <Signup togglePanel={togglePanel} />
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Auth