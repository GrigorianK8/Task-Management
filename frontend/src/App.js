import { ThemeProvider } from '@mui/material'
import { darkTheme } from './theme/darktheme'
import Navbar from './components/navbar/Navbar'
import Home from './components/home/Home'

function App() {
	return (
		<ThemeProvider theme={darkTheme}>
			<Navbar />
			<Home />
		</ThemeProvider>
	)
}

export default App
