import { ThemeProvider } from '@mui/material'
import { darkTheme } from './theme/darktheme'
import Navbar from './components/navbar/Navbar'
import Home from './components/home/Home'
import Auth from './components/auth/Auth'

function App() {
	const user = true;
	return (
		<ThemeProvider theme={darkTheme}>
			{user?<div>
			  <Navbar />
			  <Home />
			</div>:<Auth />}
		</ThemeProvider>
	)
}

export default App
