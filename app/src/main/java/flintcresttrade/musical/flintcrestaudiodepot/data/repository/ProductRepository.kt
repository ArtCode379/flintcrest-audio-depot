package flintcresttrade.musical.flintcrestaudiodepot.data.repository

import flintcresttrade.musical.flintcrestaudiodepot.data.model.Product
import flintcresttrade.musical.flintcrestaudiodepot.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products = listOf(
        Product(
            1, "Aurora S-Style Electric Guitar", "A versatile alder-body electric guitar with a comfortable maple neck, three responsive pickups, and a bright finish for stage and studio.",
            ProductCategory.GUITARS,
            429.00, "https://images.unsplash.com/photo-1564186763535-ebb21ef5277f?w=1200"),
        
        Product(
            2, "Cedar Hall Acoustic Guitar", "A warm, balanced solid-top acoustic with a slim neck and natural projection for songwriting, lessons, and intimate performances.",
            ProductCategory.GUITARS,
            349.00, "https://images.unsplash.com/photo-1510915361894-db8b60106cb1?w=1200"),
        
        Product(
            3, "StageKey 88 Digital Piano", "Weighted hammer-action keys, expressive piano voices, split mode, and USB MIDI make this a dependable home and performance instrument.",
            ProductCategory.KEYS,
            799.00, "https://images.unsplash.com/photo-1520523839897-bd0b52f945a0?w=1200"),
        
        Product(
            4, "Pulse Mini Synthesizer", "A compact analogue-inspired synthesizer with tactile controls, punchy bass presets, sequencer, and headphone output.",
            ProductCategory.KEYS,
            289.00, "https://images.unsplash.com/photo-1598488035139-bdbb2231ce04?w=1200"),
        
        Product(
            5, "Foundry Five-Piece Drum Kit", "A complete poplar-shell kit with hardware and cymbals, tuned for a focused attack and full low end.",
            ProductCategory.DRUMS,
            649.00, "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=1200"),
        
        Product(
            6, "VocalCraft Dynamic Microphone", "A rugged cardioid vocal microphone with clear mids, controlled proximity effect, and excellent rejection on loud stages.",
            ProductCategory.MICROPHONES,
            99.00, "https://images.unsplash.com/photo-1590602847861-f357a9332bbc?w=1200"),
        
        Product(
            7, "Studio Arc Condenser Microphone", "A large-diaphragm condenser designed for detailed vocals, acoustic instruments, and voice work, supplied with shock mount.",
            ProductCategory.MICROPHONES,
            219.00, "https://images.unsplash.com/photo-1593697821252-0c9137d9fc45?w=1200"),
        
        Product(
            8, "Reference One Headphones", "Closed-back monitoring headphones with replaceable pads, accurate bass, and a folding design for sessions on the move.",
            ProductCategory.HEADPHONES,
            159.00, "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=1200"),
        
        Product(
            9, "OpenField Mixing Headphones", "Open-back drivers deliver a spacious soundstage and honest detail for long editing and mixing sessions.",
            ProductCategory.HEADPHONES,
            239.00, "https://images.unsplash.com/photo-1484704849700-f032a568e944?w=1200"),
        
        Product(
            10, "NearPoint 5 Studio Monitors", "A matched pair of compact active monitors with room trim controls and precise imaging for project studios.",
            ProductCategory.SPEAKERS,
            399.00, "https://images.unsplash.com/photo-1545454675-3531b543be5d?w=1200"),
        
        Product(
            11, "RoadSound Portable Speaker", "Battery-powered sound with Bluetooth, microphone input, and a durable grab-and-go enclosure.",
            ProductCategory.SPEAKERS,
            279.00, "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=1200"),
        
        Product(
            12, "StudioLink 2 Audio Interface", "Two low-noise preamps, direct monitoring, MIDI, and high-resolution conversion in a solid desktop interface.",
            ProductCategory.INTERFACES,
            189.00, "https://images.unsplash.com/photo-1598653222000-6b7b7a552625?w=1200"),
        
        Product(
            13, "StudioLink 8 Rack Interface", "Eight preamps, ADAT expansion, flexible routing, and dependable drivers for growing recording setups.",
            ProductCategory.INTERFACES,
            699.00, "https://images.unsplash.com/photo-1516280440614-37939bbacd81?w=1200"),
        
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
