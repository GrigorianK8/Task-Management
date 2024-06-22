import React from 'react'

const TaskCard = () => {
  return (
		<div>
			<div className='card lg:flex justify-between'>
				<div className='lg:flex gap-5 items-center space-y-2 w-[90%] lg:w-[70%]'>
					<div className=''>
						<img className='lg:w-[7rem] lg:h-[7rem] object-cover'
							src='https://cdn.pixabay.com/photo/2019/05/14/23/36/ferrari-4203826_1280.jpg'
							alt=''
						/>
					</div>
          <div className='space-y-5'>
            <div className='space-y-2'>
              <h1 className='font-bold text-lg'>Car Rental Website</h1>
              <p className='text-gray-500 text-sm'>
                use latest frameworks and technologies to make this website
              </p>
            </div>
            
            <div className='flex flex-wrap gap-2 items-center'>
              {[1,1,1,1].map((item) =><span className='py-1 px-5 rounded-full techStack'>
                Angular
              </span>)}
            </div>
          </div>
				</div>
			</div>
		</div>
	)
}

export default TaskCard