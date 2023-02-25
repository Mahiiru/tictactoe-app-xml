package com.mahiiru.tictactoeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.mahiiru.tictactoeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private var isPlayerXTurn : Boolean = true
    private var isPlayerOTurn : Boolean = false

    private lateinit var binding: ActivityMainBinding
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        game = Game()
        initListeners()
        refreshGameUI()
    }

    private fun refreshGameUI() {
        if(isPlayerXTurn){
            binding.tvCurrentPlayer.text = game.playerX.type
        }else if(isPlayerOTurn){
            binding.tvCurrentPlayer.text = game.playerO.type
        }
        refreshGridUI()
    }

    private fun refreshGridUI() {
        game.gameMap.forEach { (idKey,idValue) -> bindingGameMapValues(idKey,idValue)}
    }

    private fun bindingGameMapValues(idKey: String, idValue: String) {
        val textView = binding.root.findViewById<TextView>(
            binding.root.resources.getIdentifier(
                idKey, "id", binding.root.context.packageName
            )
        )
        textView.text = idValue
    }

    private fun initListeners() {
        binding.cv0x0.setOnClickListener { clickPlay(binding.root.resources.getResourceName(binding.tv0x0.id)) }
        binding.cv0x1.setOnClickListener { clickPlay(binding.root.resources.getResourceName(binding.tv0x1.id)) }
        binding.cv0x2.setOnClickListener { clickPlay(binding.root.resources.getResourceName(binding.tv0x2.id)) }
        binding.cv1x0.setOnClickListener { clickPlay(binding.root.resources.getResourceName(binding.tv1x0.id)) }
        binding.cv1x1.setOnClickListener { clickPlay(binding.root.resources.getResourceName(binding.tv1x1.id)) }
        binding.cv1x2.setOnClickListener { clickPlay(binding.root.resources.getResourceName(binding.tv1x2.id)) }
        binding.cv2x0.setOnClickListener { clickPlay(binding.root.resources.getResourceName(binding.tv2x0.id)) }
        binding.cv2x1.setOnClickListener { clickPlay(binding.root.resources.getResourceName(binding.tv2x1.id)) }
        binding.cv2x2.setOnClickListener { clickPlay(binding.root.resources.getResourceName(binding.tv2x2.id)) }
    }

    private fun clickPlay(resourceName: String?) {
        val play = resourceName!!.substringAfter("id/")
        if(game.isValidNewPlay(play) && !game.isGameOver){
            game.gameNewPlay(play,isPlayerXTurn,isPlayerOTurn)
            isPlayerXTurn = !isPlayerXTurn
            isPlayerOTurn = !isPlayerOTurn
            refreshGameUI()
        }
        if(game.isGameOver){
            if(game.playerX.winner){
                binding.tvWinner.text = game.playerX.playerHasWon()
            }else if(game.playerO.winner){
                binding.tvWinner.text = game.playerO.playerHasWon()
            }
        }
    }


}