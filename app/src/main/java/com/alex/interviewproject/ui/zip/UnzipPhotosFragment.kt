package com.alex.interviewproject.ui.zip

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.alex.interviewproject.R
import com.alex.interviewproject.common.CommonFunctions.getBMOptions
import com.alex.interviewproject.framework.zip.FileDownloadService
import com.alex.interviewproject.framework.zip.FileDownloadService.FileDownloader
import com.alex.interviewproject.framework.zip.FileDownloadService.OnDownloadStatusListener
import com.alex.interviewproject.framework.zip.FileUtils
import kotlinx.android.synthetic.main.unzip_photos_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class UnzipPhotosFragment : Fragment() {

    private lateinit var FilePathStrings: Array<String?>
    private var listFile: Array<File>?=null
    private lateinit var photosAdapter: ZipPhotosAdapter

    private var zipSource:String?=null

    private val viewModel: UnzipPhotosViewModel by viewModels()
    private var downloadStarted:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.unzip_photos_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        setUpOnClickListeners()
        setUpAdapter()
        //downloadZipFile()
        text_input_edit_text.setText(R.string.myZipFile)

        with(viewModel.arrayList){
            if(this.isNotEmpty()){
                photosAdapter.submitList(this)
            }
        }
    }

    private fun setUpAdapter() {
        photosAdapter = ZipPhotosAdapter()
        recyclerView.apply {
            adapter = photosAdapter
        }
    }

    private fun setUpOnClickListeners() {
        input_text_zip.setEndIconOnClickListener {
            Log.d("TAG", "Clicks")
            if (!downloadStarted) {
                zipSource?.let {
                    if (it.endsWith(".zip")) {
                        downloadStarted = true
                        downloadZipFile(it)
                    } else {
                        input_text_zip.error = "wrong name!"
                    }
                }
            }
        }


        text_input_edit_text?.apply {
            doAfterTextChanged {
                Log.d("TAG","Text changes")
                zipSource = it.toString()
            }
        }
    }

    private fun downloadZipFile(url:String) {

        val path: String = FileUtils.getDataDir(context).absolutePath

        val fileName = "sample_download"
        var file = File(path, fileName)

        val localPath: String = file.absolutePath
        val unzipPath: String = FileUtils.getDataDir(context, "ExtractLoc").absolutePath
        Log.d("TAG", "path is : $unzipPath")

        val downloadRequest = FileDownloadService.DownloadRequest(url, localPath).apply {
            isRequiresUnzip = true
            isDeleteZipAfterExtract = false
            unzipAtFilePath = unzipPath
        }

        val listener: OnDownloadStatusListener = object : OnDownloadStatusListener {
            override fun onDownloadStarted() {
                Log.d("TAG", "onDownloadStarted")
                progressBar.visibility=View.VISIBLE
            }
            override fun onDownloadCompleted() {
                lifecycleScope.launch {
                    fetchData(unzipPath)
                }
            }
            override fun onDownloadFailed() {
                Log.d("TAG", "onDownloadFailed")
                Toast.makeText(requireContext(), "onDownloadFailed", Toast.LENGTH_SHORT).show()
            }
            override fun onDownloadProgress(progress: Int) {
                Log.d("TAG", "progress: $progress")
            }
        }

        val downloader = FileDownloader.getInstance(downloadRequest, listener)
        downloader.download(context)
    }

    private fun fetchData(unzipPath:String) {
        try {
            Log.d("TAG", "onDownloadCompleted")

            var file1 = File(unzipPath)
            for (file in file1.listFiles()) {
                Log.d("TAG", "file : ${file.path}")
            }
            Log.d("TAG", "files : ${file1.listFiles()}")
            if (file1.isDirectory) {

                listFile = file1.listFiles()

                FilePathStrings = arrayOfNulls(listFile!!.size)

                for (i in listFile!!.indices) {
                    FilePathStrings[i] = listFile!![i].absolutePath
                }
            }

            viewModel.arrayList = ArrayList<Photo>().apply {
                FilePathStrings.forEach {
                    if (it != null) {
                        val photo: Photo
                        val bmpOptions = getBMOptions(200, 200)
                        Log.d("TAG", "$it")
                        val i = BitmapFactory.decodeFile(
                            it, bmpOptions
                        )
                        this.add(Photo(i))
                    }
                }
            }
            photosAdapter.submitList(viewModel.arrayList)

            downloadStarted = false
            progressBar.visibility = View.GONE
        }catch (e:Exception){
            //
                downloadStarted = false
                progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            //}
        }
    }

}