/*
 * Copyright (C) 2018 CyberAgent, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.co.cyberagent.android.gpuimage.sample

import android.app.AlertDialog
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.PointF
import android.opengl.Matrix
import jp.co.cyberagent.android.gpuimage.filter.*
import java.util.*

object GPUImageFilterTools {
    fun showDialog(
        context: Context,
        listener: (filter: GPUImageFilter) -> Unit
    ) {
        val filters = FilterList().apply {
            addFilter("Contrast 对比度", FilterType.CONTRAST)
            addFilter("Invert 反色", FilterType.INVERT)
            addFilter("Pixelation 像素化", FilterType.PIXELATION)
            addFilter("Hue 色相", FilterType.HUE)
            addFilter("Gamma 伽马", FilterType.GAMMA)
            addFilter("Brightness 亮度", FilterType.BRIGHTNESS)
            addFilter("Sepia 棕褐色", FilterType.SEPIA)
            addFilter("Grayscale 灰度", FilterType.GRAYSCALE)
            addFilter("Sharpness 锐化", FilterType.SHARPEN)
            addFilter("Sobel Edge Detection Sobel边缘检测", FilterType.SOBEL_EDGE_DETECTION)
            addFilter("Threshold Edge Detection 阈值边缘检测", FilterType.THRESHOLD_EDGE_DETECTION)
            addFilter("3x3 Convolution 3x3卷积", FilterType.THREE_X_THREE_CONVOLUTION)
            addFilter("Emboss 浮雕", FilterType.EMBOSS)
            addFilter("Posterize 色调分离", FilterType.POSTERIZE)
            addFilter("Grouped filters 滤镜组", FilterType.FILTER_GROUP)
            addFilter("Saturation 饱和度", FilterType.SATURATION)
            addFilter("Exposure 曝光", FilterType.EXPOSURE)
            addFilter("Highlight Shadow 高光阴影", FilterType.HIGHLIGHT_SHADOW)
            addFilter("Monochrome 单色", FilterType.MONOCHROME)
            addFilter("Opacity 不透明度", FilterType.OPACITY)
            addFilter("RGB RGB", FilterType.RGB)
            addFilter("White Balance 白平衡", FilterType.WHITE_BALANCE)
            addFilter("Vignette 晕影", FilterType.VIGNETTE)
            addFilter("ToneCurve 色调曲线", FilterType.TONE_CURVE)
            addFilter("Luminance 明度", FilterType.LUMINANCE)
            addFilter("Luminance Threshold 明度阈值", FilterType.LUMINANCE_THRESHSOLD)
            addFilter("Blend (Difference) 差值混合", FilterType.BLEND_DIFFERENCE)
            addFilter("Blend (Source Over) 覆盖混合", FilterType.BLEND_SOURCE_OVER)
            addFilter("Blend (Color Burn) 颜色加深混合", FilterType.BLEND_COLOR_BURN)
            addFilter("Blend (Color Dodge) 颜色减淡混合", FilterType.BLEND_COLOR_DODGE)
            addFilter("Blend (Darken) 变暗混合", FilterType.BLEND_DARKEN)
            addFilter("Blend (Dissolve) 溶解混合", FilterType.BLEND_DISSOLVE)
            addFilter("Blend (Exclusion) 排除混合", FilterType.BLEND_EXCLUSION)
            addFilter("Blend (Hard Light) 强光混合", FilterType.BLEND_HARD_LIGHT)
            addFilter("Blend (Lighten) 变亮混合", FilterType.BLEND_LIGHTEN)
            addFilter("Blend (Add) 叠加混合", FilterType.BLEND_ADD)
            addFilter("Blend (Divide) 除法混合", FilterType.BLEND_DIVIDE)
            addFilter("Blend (Multiply) 乘法混合", FilterType.BLEND_MULTIPLY)
            addFilter("Blend (Overlay) 叠加混合", FilterType.BLEND_OVERLAY)
            addFilter("Blend (Screen) 滤色混合", FilterType.BLEND_SCREEN)
            addFilter("Blend (Alpha) 透明度混合", FilterType.BLEND_ALPHA)
            addFilter("Blend (Color) 颜色混合", FilterType.BLEND_COLOR)
            addFilter("Blend (Hue) 色相混合", FilterType.BLEND_HUE)
            addFilter("Blend (Saturation) 饱和度混合", FilterType.BLEND_SATURATION)
            addFilter("Blend (Luminosity) 明度混合", FilterType.BLEND_LUMINOSITY)
            addFilter("Blend (Linear Burn) 线性加深混合", FilterType.BLEND_LINEAR_BURN)
            addFilter("Blend (Soft Light) 柔光混合", FilterType.BLEND_SOFT_LIGHT)
            addFilter("Blend (Subtract) 减法混合", FilterType.BLEND_SUBTRACT)
            addFilter("Blend (Chroma Key) 色度键混合", FilterType.BLEND_CHROMA_KEY)
            addFilter("Blend (Normal) 普通混合", FilterType.BLEND_NORMAL)
            addFilter("Lookup (Amatorka) 查找表(Amatorka)", FilterType.LOOKUP_AMATORKA)
            addFilter("Gaussian Blur 高斯模糊", FilterType.GAUSSIAN_BLUR)
            addFilter("Crosshatch 交叉阴影", FilterType.CROSSHATCH)
            addFilter("Box Blur 方框模糊", FilterType.BOX_BLUR)
            addFilter("CGA Color Space CGA色彩空间", FilterType.CGA_COLORSPACE)
            addFilter("Dilation 膨胀", FilterType.DILATION)
            addFilter("Kuwahara Kuwahara滤镜", FilterType.KUWAHARA)
            addFilter("RGB Dilation RGB膨胀", FilterType.RGB_DILATION)
            addFilter("Sketch 素描", FilterType.SKETCH)
            addFilter("Toon 卡通", FilterType.TOON)
            addFilter("Smooth Toon 平滑卡通", FilterType.SMOOTH_TOON)
            addFilter("Halftone 半色调", FilterType.HALFTONE)
            addFilter("Bulge Distortion 凸出扭曲", FilterType.BULGE_DISTORTION)
            addFilter("Glass Sphere 玻璃球", FilterType.GLASS_SPHERE)
            addFilter("Haze 雾化", FilterType.HAZE)
            addFilter("Laplacian 拉普拉斯", FilterType.LAPLACIAN)
            addFilter("Non Maximum Suppression 非极大值抑制", FilterType.NON_MAXIMUM_SUPPRESSION)
            addFilter("Sphere Refraction 球面折射", FilterType.SPHERE_REFRACTION)
            addFilter("Swirl 漩涡扭曲", FilterType.SWIRL)
            addFilter("Weak Pixel Inclusion 弱像素包含", FilterType.WEAK_PIXEL_INCLUSION)
            addFilter("False Color 伪彩色", FilterType.FALSE_COLOR)
            addFilter("Color Balance 色彩平衡", FilterType.COLOR_BALANCE)
            addFilter("Levels Min (Mid Adjust) 色阶最小(中间调整)", FilterType.LEVELS_FILTER_MIN)
            addFilter("Bilateral Blur 双边模糊", FilterType.BILATERAL_BLUR)
            addFilter("Zoom Blur 缩放模糊", FilterType.ZOOM_BLUR)
            addFilter("Transform (2-D) 二维变换", FilterType.TRANSFORM2D)
            addFilter("Solarize 曝光过度", FilterType.SOLARIZE)
            addFilter("Vibrance 活力", FilterType.VIBRANCE)
        }

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose a filter")
        builder.setItems(filters.names.toTypedArray()) { _, item ->
            listener(createFilterForType(context, filters.filters[item]))
        }
        builder.create().show()
    }

    private fun createFilterForType(context: Context, type: FilterType): GPUImageFilter {
        return when (type) {
            FilterType.CONTRAST -> GPUImageContrastFilter(2.0f)
            FilterType.GAMMA -> GPUImageGammaFilter(2.0f)
            FilterType.INVERT -> GPUImageColorInvertFilter()
            FilterType.PIXELATION -> GPUImagePixelationFilter()
            FilterType.HUE -> GPUImageHueFilter(90.0f)
            FilterType.BRIGHTNESS -> GPUImageBrightnessFilter(1.5f)
            FilterType.GRAYSCALE -> GPUImageGrayscaleFilter()
            FilterType.SEPIA -> GPUImageSepiaToneFilter()
            FilterType.SHARPEN -> GPUImageSharpenFilter()
            FilterType.SOBEL_EDGE_DETECTION -> GPUImageSobelEdgeDetectionFilter()
            FilterType.THRESHOLD_EDGE_DETECTION -> GPUImageThresholdEdgeDetectionFilter()
            FilterType.THREE_X_THREE_CONVOLUTION -> GPUImage3x3ConvolutionFilter()
            FilterType.EMBOSS -> GPUImageEmbossFilter()
            FilterType.POSTERIZE -> GPUImagePosterizeFilter()
            FilterType.FILTER_GROUP -> GPUImageFilterGroup(
                listOf(
                    GPUImageContrastFilter(),
                    GPUImageDirectionalSobelEdgeDetectionFilter(),
                    GPUImageGrayscaleFilter()
                )
            )
            FilterType.SATURATION -> GPUImageSaturationFilter(1.0f)
            FilterType.EXPOSURE -> GPUImageExposureFilter(0.0f)
            FilterType.HIGHLIGHT_SHADOW -> GPUImageHighlightShadowFilter(
                0.0f,
                1.0f
            )
            FilterType.MONOCHROME -> GPUImageMonochromeFilter(
                1.0f, floatArrayOf(0.6f, 0.45f, 0.3f, 1.0f)
            )
            FilterType.OPACITY -> GPUImageOpacityFilter(1.0f)
            FilterType.RGB -> GPUImageRGBFilter(1.0f, 1.0f, 1.0f)
            FilterType.WHITE_BALANCE -> GPUImageWhiteBalanceFilter(
                5000.0f,
                0.0f
            )
            FilterType.VIGNETTE -> GPUImageVignetteFilter(
                PointF(0.5f, 0.5f),
                floatArrayOf(0.0f, 0.0f, 0.0f),
                0.3f,
                0.75f
            )
            FilterType.TONE_CURVE -> GPUImageToneCurveFilter().apply {
                setFromCurveFileInputStream(context.resources.openRawResource(R.raw.tone_cuver_sample))
            }
            FilterType.LUMINANCE -> GPUImageLuminanceFilter()
            FilterType.LUMINANCE_THRESHSOLD -> GPUImageLuminanceThresholdFilter(0.5f)
            FilterType.BLEND_DIFFERENCE -> createBlendFilter(
                context,
                GPUImageDifferenceBlendFilter::class.java
            )
            FilterType.BLEND_SOURCE_OVER -> createBlendFilter(
                context,
                GPUImageSourceOverBlendFilter::class.java
            )
            FilterType.BLEND_COLOR_BURN -> createBlendFilter(
                context,
                GPUImageColorBurnBlendFilter::class.java
            )
            FilterType.BLEND_COLOR_DODGE -> createBlendFilter(
                context,
                GPUImageColorDodgeBlendFilter::class.java
            )
            FilterType.BLEND_DARKEN -> createBlendFilter(
                context,
                GPUImageDarkenBlendFilter::class.java
            )
            FilterType.BLEND_DISSOLVE -> createBlendFilter(
                context,
                GPUImageDissolveBlendFilter::class.java
            )
            FilterType.BLEND_EXCLUSION -> createBlendFilter(
                context,
                GPUImageExclusionBlendFilter::class.java
            )

            FilterType.BLEND_HARD_LIGHT -> createBlendFilter(
                context,
                GPUImageHardLightBlendFilter::class.java
            )
            FilterType.BLEND_LIGHTEN -> createBlendFilter(
                context,
                GPUImageLightenBlendFilter::class.java
            )
            FilterType.BLEND_ADD -> createBlendFilter(
                context,
                GPUImageAddBlendFilter::class.java
            )
            FilterType.BLEND_DIVIDE -> createBlendFilter(
                context,
                GPUImageDivideBlendFilter::class.java
            )
            FilterType.BLEND_MULTIPLY -> createBlendFilter(
                context,
                GPUImageMultiplyBlendFilter::class.java
            )
            FilterType.BLEND_OVERLAY -> createBlendFilter(
                context,
                GPUImageOverlayBlendFilter::class.java
            )
            FilterType.BLEND_SCREEN -> createBlendFilter(
                context,
                GPUImageScreenBlendFilter::class.java
            )
            FilterType.BLEND_ALPHA -> createBlendFilter(
                context,
                GPUImageAlphaBlendFilter::class.java
            )
            FilterType.BLEND_COLOR -> createBlendFilter(
                context,
                GPUImageColorBlendFilter::class.java
            )
            FilterType.BLEND_HUE -> createBlendFilter(
                context,
                GPUImageHueBlendFilter::class.java
            )
            FilterType.BLEND_SATURATION -> createBlendFilter(
                context,
                GPUImageSaturationBlendFilter::class.java
            )
            FilterType.BLEND_LUMINOSITY -> createBlendFilter(
                context,
                GPUImageLuminosityBlendFilter::class.java
            )
            FilterType.BLEND_LINEAR_BURN -> createBlendFilter(
                context,
                GPUImageLinearBurnBlendFilter::class.java
            )
            FilterType.BLEND_SOFT_LIGHT -> createBlendFilter(
                context,
                GPUImageSoftLightBlendFilter::class.java
            )
            FilterType.BLEND_SUBTRACT -> createBlendFilter(
                context,
                GPUImageSubtractBlendFilter::class.java
            )
            FilterType.BLEND_CHROMA_KEY -> createBlendFilter(
                context,
                GPUImageChromaKeyBlendFilter::class.java
            )
            FilterType.BLEND_NORMAL -> createBlendFilter(
                context,
                GPUImageNormalBlendFilter::class.java
            )

            FilterType.LOOKUP_AMATORKA -> GPUImageLookupFilter().apply {
                bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.lookup_amatorka)
            }
            FilterType.GAUSSIAN_BLUR -> GPUImageGaussianBlurFilter()
            FilterType.CROSSHATCH -> GPUImageCrosshatchFilter()
            FilterType.BOX_BLUR -> GPUImageBoxBlurFilter()
            FilterType.CGA_COLORSPACE -> GPUImageCGAColorspaceFilter()
            FilterType.DILATION -> GPUImageDilationFilter()
            FilterType.KUWAHARA -> GPUImageKuwaharaFilter()
            FilterType.RGB_DILATION -> GPUImageRGBDilationFilter()
            FilterType.SKETCH -> GPUImageSketchFilter()
            FilterType.TOON -> GPUImageToonFilter()
            FilterType.SMOOTH_TOON -> GPUImageSmoothToonFilter()
            FilterType.BULGE_DISTORTION -> GPUImageBulgeDistortionFilter()
            FilterType.GLASS_SPHERE -> GPUImageGlassSphereFilter()
            FilterType.HAZE -> GPUImageHazeFilter()
            FilterType.LAPLACIAN -> GPUImageLaplacianFilter()
            FilterType.NON_MAXIMUM_SUPPRESSION -> GPUImageNonMaximumSuppressionFilter()
            FilterType.SPHERE_REFRACTION -> GPUImageSphereRefractionFilter()
            FilterType.SWIRL -> GPUImageSwirlFilter()
            FilterType.WEAK_PIXEL_INCLUSION -> GPUImageWeakPixelInclusionFilter()
            FilterType.FALSE_COLOR -> GPUImageFalseColorFilter()
            FilterType.COLOR_BALANCE -> GPUImageColorBalanceFilter()
            FilterType.LEVELS_FILTER_MIN -> GPUImageLevelsFilter()
            FilterType.HALFTONE -> GPUImageHalftoneFilter()
            FilterType.BILATERAL_BLUR -> GPUImageBilateralBlurFilter()
            FilterType.ZOOM_BLUR -> GPUImageZoomBlurFilter()
            FilterType.TRANSFORM2D -> GPUImageTransformFilter()
            FilterType.SOLARIZE -> GPUImageSolarizeFilter()
            FilterType.VIBRANCE -> GPUImageVibranceFilter()
        }
    }

    private fun createBlendFilter(
        context: Context,
        filterClass: Class<out GPUImageTwoInputFilter>
    ): GPUImageFilter {
        return try {
            filterClass.newInstance().apply {
                bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            GPUImageFilter()
        }
    }

    private enum class FilterType {
        CONTRAST, GRAYSCALE, SHARPEN, SEPIA, SOBEL_EDGE_DETECTION, THRESHOLD_EDGE_DETECTION, THREE_X_THREE_CONVOLUTION, FILTER_GROUP, EMBOSS, POSTERIZE, GAMMA, BRIGHTNESS, INVERT, HUE, PIXELATION,
        SATURATION, EXPOSURE, HIGHLIGHT_SHADOW, MONOCHROME, OPACITY, RGB, WHITE_BALANCE, VIGNETTE, TONE_CURVE, LUMINANCE, LUMINANCE_THRESHSOLD, BLEND_COLOR_BURN, BLEND_COLOR_DODGE, BLEND_DARKEN,
        BLEND_DIFFERENCE, BLEND_DISSOLVE, BLEND_EXCLUSION, BLEND_SOURCE_OVER, BLEND_HARD_LIGHT, BLEND_LIGHTEN, BLEND_ADD, BLEND_DIVIDE, BLEND_MULTIPLY, BLEND_OVERLAY, BLEND_SCREEN, BLEND_ALPHA,
        BLEND_COLOR, BLEND_HUE, BLEND_SATURATION, BLEND_LUMINOSITY, BLEND_LINEAR_BURN, BLEND_SOFT_LIGHT, BLEND_SUBTRACT, BLEND_CHROMA_KEY, BLEND_NORMAL, LOOKUP_AMATORKA,
        GAUSSIAN_BLUR, CROSSHATCH, BOX_BLUR, CGA_COLORSPACE, DILATION, KUWAHARA, RGB_DILATION, SKETCH, TOON, SMOOTH_TOON, BULGE_DISTORTION, GLASS_SPHERE, HAZE, LAPLACIAN, NON_MAXIMUM_SUPPRESSION,
        SPHERE_REFRACTION, SWIRL, WEAK_PIXEL_INCLUSION, FALSE_COLOR, COLOR_BALANCE, LEVELS_FILTER_MIN, BILATERAL_BLUR, ZOOM_BLUR, HALFTONE, TRANSFORM2D, SOLARIZE, VIBRANCE
    }

    private class FilterList {
        val names: MutableList<String> = LinkedList()
        val filters: MutableList<FilterType> = LinkedList()

        fun addFilter(name: String, filter: FilterType) {
            names.add(name)
            filters.add(filter)
        }
    }

    class FilterAdjuster(filter: GPUImageFilter) {
        private val adjuster: Adjuster<out GPUImageFilter>?

        init {
            adjuster = when (filter) {
                is GPUImageSharpenFilter -> SharpnessAdjuster(filter)
                is GPUImageSepiaToneFilter -> SepiaAdjuster(filter)
                is GPUImageContrastFilter -> ContrastAdjuster(filter)
                is GPUImageGammaFilter -> GammaAdjuster(filter)
                is GPUImageBrightnessFilter -> BrightnessAdjuster(filter)
                is GPUImageSobelEdgeDetectionFilter -> SobelAdjuster(filter)
                is GPUImageThresholdEdgeDetectionFilter -> ThresholdAdjuster(filter)
                is GPUImage3x3ConvolutionFilter -> ThreeXThreeConvolutionAjuster(filter)
                is GPUImageEmbossFilter -> EmbossAdjuster(filter)
                is GPUImage3x3TextureSamplingFilter -> GPU3x3TextureAdjuster(filter)
                is GPUImageHueFilter -> HueAdjuster(filter)
                is GPUImagePosterizeFilter -> PosterizeAdjuster(filter)
                is GPUImagePixelationFilter -> PixelationAdjuster(filter)
                is GPUImageSaturationFilter -> SaturationAdjuster(filter)
                is GPUImageExposureFilter -> ExposureAdjuster(filter)
                is GPUImageHighlightShadowFilter -> HighlightShadowAdjuster(filter)
                is GPUImageMonochromeFilter -> MonochromeAdjuster(filter)
                is GPUImageOpacityFilter -> OpacityAdjuster(filter)
                is GPUImageRGBFilter -> RGBAdjuster(filter)
                is GPUImageWhiteBalanceFilter -> WhiteBalanceAdjuster(filter)
                is GPUImageVignetteFilter -> VignetteAdjuster(filter)
                is GPUImageLuminanceThresholdFilter -> LuminanceThresholdAdjuster(filter)
                is GPUImageDissolveBlendFilter -> DissolveBlendAdjuster(filter)
                is GPUImageGaussianBlurFilter -> GaussianBlurAdjuster(filter)
                is GPUImageCrosshatchFilter -> CrosshatchBlurAdjuster(filter)
                is GPUImageBulgeDistortionFilter -> BulgeDistortionAdjuster(filter)
                is GPUImageGlassSphereFilter -> GlassSphereAdjuster(filter)
                is GPUImageHazeFilter -> HazeAdjuster(filter)
                is GPUImageSphereRefractionFilter -> SphereRefractionAdjuster(filter)
                is GPUImageSwirlFilter -> SwirlAdjuster(filter)
                is GPUImageColorBalanceFilter -> ColorBalanceAdjuster(filter)
                is GPUImageLevelsFilter -> LevelsMinMidAdjuster(filter)
                is GPUImageBilateralBlurFilter -> BilateralAdjuster(filter)
                is GPUImageTransformFilter -> RotateAdjuster(filter)
                is GPUImageSolarizeFilter -> SolarizeAdjuster(filter)
                is GPUImageVibranceFilter -> VibranceAdjuster(filter)
                else -> null
            }
        }

        fun canAdjust(): Boolean {
            return adjuster != null
        }

        fun adjust(percentage: Int) {
            adjuster?.adjust(percentage)
        }

        private abstract inner class Adjuster<T : GPUImageFilter>(protected val filter: T) {

            abstract fun adjust(percentage: Int)

            protected fun range(percentage: Int, start: Float, end: Float): Float {
                return (end - start) * percentage / 100.0f + start
            }

            protected fun range(percentage: Int, start: Int, end: Int): Int {
                return (end - start) * percentage / 100 + start
            }
        }

        private inner class SharpnessAdjuster(filter: GPUImageSharpenFilter) :
            Adjuster<GPUImageSharpenFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setSharpness(range(percentage, -4.0f, 4.0f))
            }
        }

        private inner class PixelationAdjuster(filter: GPUImagePixelationFilter) :
            Adjuster<GPUImagePixelationFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setPixel(range(percentage, 1.0f, 100.0f))
            }
        }

        private inner class HueAdjuster(filter: GPUImageHueFilter) :
            Adjuster<GPUImageHueFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setHue(range(percentage, 0.0f, 360.0f))
            }
        }

        private inner class ContrastAdjuster(filter: GPUImageContrastFilter) :
            Adjuster<GPUImageContrastFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setContrast(range(percentage, 0.0f, 2.0f))
            }
        }

        private inner class GammaAdjuster(filter: GPUImageGammaFilter) :
            Adjuster<GPUImageGammaFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setGamma(range(percentage, 0.0f, 3.0f))
            }
        }

        private inner class BrightnessAdjuster(filter: GPUImageBrightnessFilter) :
            Adjuster<GPUImageBrightnessFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setBrightness(range(percentage, -1.0f, 1.0f))
            }
        }

        private inner class SepiaAdjuster(filter: GPUImageSepiaToneFilter) :
            Adjuster<GPUImageSepiaToneFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setIntensity(range(percentage, 0.0f, 2.0f))
            }
        }

        private inner class SobelAdjuster(filter: GPUImageSobelEdgeDetectionFilter) :
            Adjuster<GPUImageSobelEdgeDetectionFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setLineSize(range(percentage, 0.0f, 5.0f))
            }
        }

        private inner class ThresholdAdjuster(filter: GPUImageThresholdEdgeDetectionFilter) :
            Adjuster<GPUImageThresholdEdgeDetectionFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setLineSize(range(percentage, 0.0f, 5.0f))
                filter.setThreshold(0.9f)
            }
        }

        private inner class ThreeXThreeConvolutionAjuster(filter: GPUImage3x3ConvolutionFilter) :
            Adjuster<GPUImage3x3ConvolutionFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setConvolutionKernel(
                    floatArrayOf(-1.0f, 0.0f, 1.0f, -2.0f, 0.0f, 2.0f, -1.0f, 0.0f, 1.0f)
                )
            }
        }

        private inner class EmbossAdjuster(filter: GPUImageEmbossFilter) :
            Adjuster<GPUImageEmbossFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.intensity = range(percentage, 0.0f, 4.0f)
            }
        }

        private inner class PosterizeAdjuster(filter: GPUImagePosterizeFilter) :
            Adjuster<GPUImagePosterizeFilter>(filter) {
            override fun adjust(percentage: Int) {
                // In theorie to 256, but only first 50 are interesting
                filter.setColorLevels(range(percentage, 1, 50))
            }
        }

        private inner class GPU3x3TextureAdjuster(filter: GPUImage3x3TextureSamplingFilter) :
            Adjuster<GPUImage3x3TextureSamplingFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setLineSize(range(percentage, 0.0f, 5.0f))
            }
        }

        private inner class SaturationAdjuster(filter: GPUImageSaturationFilter) :
            Adjuster<GPUImageSaturationFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setSaturation(range(percentage, 0.0f, 2.0f))
            }
        }

        private inner class ExposureAdjuster(filter: GPUImageExposureFilter) :
            Adjuster<GPUImageExposureFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setExposure(range(percentage, -10.0f, 10.0f))
            }
        }

        private inner class HighlightShadowAdjuster(filter: GPUImageHighlightShadowFilter) :
            Adjuster<GPUImageHighlightShadowFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setShadows(range(percentage, 0.0f, 1.0f))
                filter.setHighlights(range(percentage, 0.0f, 1.0f))
            }
        }

        private inner class MonochromeAdjuster(filter: GPUImageMonochromeFilter) :
            Adjuster<GPUImageMonochromeFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setIntensity(range(percentage, 0.0f, 1.0f))
            }
        }

        private inner class OpacityAdjuster(filter: GPUImageOpacityFilter) :
            Adjuster<GPUImageOpacityFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setOpacity(range(percentage, 0.0f, 1.0f))
            }
        }

        private inner class RGBAdjuster(filter: GPUImageRGBFilter) :
            Adjuster<GPUImageRGBFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setRed(range(percentage, 0.0f, 1.0f))
            }
        }

        private inner class WhiteBalanceAdjuster(filter: GPUImageWhiteBalanceFilter) :
            Adjuster<GPUImageWhiteBalanceFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setTemperature(range(percentage, 2000.0f, 8000.0f))
            }
        }

        private inner class VignetteAdjuster(filter: GPUImageVignetteFilter) :
            Adjuster<GPUImageVignetteFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setVignetteStart(range(percentage, 0.0f, 1.0f))
            }
        }

        private inner class LuminanceThresholdAdjuster(filter: GPUImageLuminanceThresholdFilter) :
            Adjuster<GPUImageLuminanceThresholdFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setThreshold(range(percentage, 0.0f, 1.0f))
            }
        }

        private inner class DissolveBlendAdjuster(filter: GPUImageDissolveBlendFilter) :
            Adjuster<GPUImageDissolveBlendFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setMix(range(percentage, 0.0f, 1.0f))
            }
        }

        private inner class GaussianBlurAdjuster(filter: GPUImageGaussianBlurFilter) :
            Adjuster<GPUImageGaussianBlurFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setBlurSize(range(percentage, 0.0f, 1.0f))
            }
        }

        private inner class CrosshatchBlurAdjuster(filter: GPUImageCrosshatchFilter) :
            Adjuster<GPUImageCrosshatchFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setCrossHatchSpacing(range(percentage, 0.0f, 0.06f))
                filter.setLineWidth(range(percentage, 0.0f, 0.006f))
            }
        }

        private inner class BulgeDistortionAdjuster(filter: GPUImageBulgeDistortionFilter) :
            Adjuster<GPUImageBulgeDistortionFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setRadius(range(percentage, 0.0f, 1.0f))
                filter.setScale(range(percentage, -1.0f, 1.0f))
            }
        }

        private inner class GlassSphereAdjuster(filter: GPUImageGlassSphereFilter) :
            Adjuster<GPUImageGlassSphereFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setRadius(range(percentage, 0.0f, 1.0f))
            }
        }

        private inner class HazeAdjuster(filter: GPUImageHazeFilter) :
            Adjuster<GPUImageHazeFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setDistance(range(percentage, -0.3f, 0.3f))
                filter.setSlope(range(percentage, -0.3f, 0.3f))
            }
        }

        private inner class SphereRefractionAdjuster(filter: GPUImageSphereRefractionFilter) :
            Adjuster<GPUImageSphereRefractionFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setRadius(range(percentage, 0.0f, 1.0f))
            }
        }

        private inner class SwirlAdjuster(filter: GPUImageSwirlFilter) :
            Adjuster<GPUImageSwirlFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setAngle(range(percentage, 0.0f, 2.0f))
            }
        }

        private inner class ColorBalanceAdjuster(filter: GPUImageColorBalanceFilter) :
            Adjuster<GPUImageColorBalanceFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setMidtones(
                    floatArrayOf(
                        range(percentage, 0.0f, 1.0f),
                        range(percentage / 2, 0.0f, 1.0f),
                        range(percentage / 3, 0.0f, 1.0f)
                    )
                )
            }
        }

        private inner class LevelsMinMidAdjuster(filter: GPUImageLevelsFilter) :
            Adjuster<GPUImageLevelsFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setMin(0.0f, range(percentage, 0.0f, 1.0f), 1.0f)
            }
        }

        private inner class BilateralAdjuster(filter: GPUImageBilateralBlurFilter) :
            Adjuster<GPUImageBilateralBlurFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setDistanceNormalizationFactor(range(percentage, 0.0f, 15.0f))
            }
        }

        private inner class RotateAdjuster(filter: GPUImageTransformFilter) :
            Adjuster<GPUImageTransformFilter>(filter) {
            override fun adjust(percentage: Int) {
                val transform = FloatArray(16)
                Matrix.setRotateM(transform, 0, (360 * percentage / 100).toFloat(), 0f, 0f, 1.0f)
                filter.transform3D = transform
            }
        }

        private inner class SolarizeAdjuster(filter: GPUImageSolarizeFilter) :
            Adjuster<GPUImageSolarizeFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setThreshold(range(percentage, 0.0f, 1.0f))
            }
        }

        private inner class VibranceAdjuster(filter: GPUImageVibranceFilter) :
            Adjuster<GPUImageVibranceFilter>(filter) {
            override fun adjust(percentage: Int) {
                filter.setVibrance(range(percentage, -1.2f, 1.2f))
            }
        }
    }
}
